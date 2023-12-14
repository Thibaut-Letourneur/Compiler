grammar MVaPCodeGenerator;

/*
    Optionnels réalisés:
        - Opérateurs d'assignation *=, /=, -=, --, ++
        - Booléens
        - Casts implicites
        - Casts explicites
        - Tableaux int, double et booléens
        - Passage de tableaux par adresse aux fonctions

    AdresseType a été modifié

    Note: On utilise .contains() au lieu de .equals() dans la suite du code afin de prendre en compte
    les tableaux. En effet, le type d'un tableau de int est représenté par la chaîne de caractère: 'arrayint'
    Le tableau devant alors être considéré comme un tableau et comme un int, nous pouvons tester le type du tableau
    en utilisant contains("int"). equals("int") ne fonctionnerait pas ici
*/

@members
{
    private final String RESERVED_DOUBLE_REGISTER_LABEL = "reservedDoubleRegister";
    private boolean doubleRegisterAllocated = false;

    private TablesSymboles tablesSymboles = new TablesSymboles();

    private int _cur_label = 1;
    /** générateur de nom d'étiquettes pour les boucles */
    private String getNewLabel() { return "Label" +(_cur_label++); }

    /**
    *   Cette méthode vérifie si le 'type' passé est un booléen ou non. Si c'est un booléen,
    *   un message d'erreur sera émit sur stderr et 'operator' sera utilisé pour personnaliser
    *   le message d'erreur
    *
    *   @param type Le type à vérifier
    *   @param operator Une chaîne de caractère qui sera utilisée pour personnaliser
    *   le message d'erreur. Idéalement, l'opérateur qui a tenté d'être utilisé sur
    *   le booléen
    */
    private void printErrorIfBool(String type, String operator)
    {
        if(type.contains("bool"))
            throw new Error("ERROR: The operator '" + operator + "' is not supported for the type 'boolean'");
    }

    /*
    *   Cette méthode compare deux types donnés et retourne 'true' s'ils sont égaux
    *   false sinon. Si des types tels que 'arrayint' ou 'arraydouble' sont donnés
    *   en paramètre à cette fonction, elle retournera "true" dans le cas ou le type
    *   du tableau est le même que le deuxième type donné.
    *   Par exemple, la méthode retournera true si 'arrayint' et 'int' sont donnés
    *   ou si 'arraydouble' ou 'double' sont donnés (sans se limiter à ces exemples).
    *
    *   @param firstType Premier type à comparer
    *   @param secondType Deuxième type à comparer
    */
    private boolean equalsType(String firstType, String secondType)
    {
        if(firstType.indexOf("array") != -1)
            firstType = firstType.substring(firstType.indexOf("array") + 5);//On supprime le "array"
        if(secondType.indexOf("array") != -1)
            secondType = secondType.substring(secondType.indexOf("array") + 5);//On supprime le "array"

        return firstType.equals(secondType);
    }

    /**
    *   Cette méthode emet un message d'erreur dans stderr si indexType n'est pas la châine de caractère
    *   'int'. Cela signifierait en effet que l'on essaie d'indexer un tableau avec une expression autre qu'une
    *   expression de type int
    *
    *   @param indexType Le type de l'index qui doit être vérifié
    */
    private void checkArrayIndexType(String indexType)
    {
        if(indexType.contains("double") || indexType.contains("bool"))
            throw new Error("ERROR: You cannot index an array with an index of type " + indexType);
    }

    /*
    *   Cette méthode génère les instructions permettant de stocker le flottant en sommet de pile
    *   dans le tableau à l'adresse arrayAdresse. Cette fonction s'attend à ce que la pile soit de la
    *   forme:
    *
    *   [ i1 i2 f0 f1 ]
    *   avec:
    *       - i1 = i2 les index permettant de retrouver sur la pile la case du tableau
    *                 dans laquelle stocker le flottant. Pour stocker dans tableau[1],
    *                 i1 et i2 devront valloir 2 puisque chaque flottant prend deux emplacements
    *                 sur la pile, x[0] s'étend de 0 à 1, x[1] commence donc à 2, d'où
    *                 i1 = i2 = 2
    *       - f0 l'octet bas du flottant à stocker
    *       - f1 l'octet haut du flottant à stocker
    *
    *   @param arrayAdresse L'adresse du tableau dans lequel stocker le flottant en sommet de pile
    *
    *   @return Retourne l'ensemble d'instrutions adéquat au stockage du flottant en sommet de pile dans
    *   le tableau à l'adresse 'arrayAdresse'
    */
    private String STORERF(Integer arrayAdresse)
    {
        String returnedCode = "";

        /*
        *   On a normalement à cette étape du programme une pile qui ressemble à:
        *
        *   [ r t t i i 0 1075052544 ] avec dans l'ordre:
        *   le registre réservé
        *   les deux octets d'un hypothétique tableau de double de longueur 1, t et t
        *   l'index du tableau auquel on doit stocker le flottant
        *   une deuxième fois l'index du tableau auquel on doit stocker le flottant
        *   ledit flottant sur deux octets
        */

        //On stocke l'octet du flottant qui est en haut de la pile dans le registre réservé à cet effet
        returnedCode += "STOREG " + tablesSymboles.getAdresseType(RESERVED_DOUBLE_REGISTER_LABEL).adresse + "\n";
        returnedCode += "STORER " + (0 + arrayAdresse) + "\n";//Cela va stocker l'octet bas du flottant dans l'octet bas de la case du tableau désignée
        //par l'index 'i';
        //La pile doit maintenant ressembler à [ 1075052544 0 t i ]
        //On push l'octet qu'on avait sauvegardé dans le registre
        returnedCode += "PUSHG " + tablesSymboles.getAdresseType(RESERVED_DOUBLE_REGISTER_LABEL).adresse + "\n";
        //La pile doit maintenant ressembler à [ 1075052544 0 t i 1075052544 ]
        returnedCode += "STORER " + (1 + arrayAdresse) + "\n";//Store le deuxième octet du flottant (l'octet haut) dans l'octet haut de la case du tableau
        //La pile doit maintenant ressembler à [ 1075052544 0 1075052544 ]

        return returnedCode;
    }

    /**
    *   Retourne l'instruction adéquate permettant de stocker la valeur du haut de la pile
    *   dans la variable à l'adresse 'varAdresse'.
    *
    *   @param varAdresse L'adresse de la variable dans laquelle stocker la valeur
    *   en haut de pile
    *   @param varType  Le type de la variable dans laquelle on va stocker la valeur
    *   du haut de la pile
    *   @param isArray Si 'varAdresse' désigne l'adresse du début d'un tableau
    *
    *   @return Retourne une instruction STORE adaptée en fonction des arguments
    *   passés
    */
    private String storeVar(Integer varAdresse, String varType)
    {
        String returnedCode = "";

        boolean isArray = varType.contains("array");

        if(isArray)//On agit sur un tableau
        {
            if(varAdresse < 0)//La variable qu'on a ici nous donne l'adresse du tableau.
            //On a un pointeur sur le début du tableau ici, on va donc recalculer l'adresse du tableau
                varAdresse = 0;//On a déjà ajouté l'adresse du tableau dans les index donc on a pas a tenir
                //compte de l'adresse du tableau, on met varAdresse = 0

            if(varType.contains("double"))
                returnedCode += STORERF(varAdresse);
            else
                returnedCode += "STORER " + varAdresse + "\n";
        }
        else
        {
            String storeOperator = "STORE" + (varAdresse < 0 ? "L" : "G") + " ";

            if(varType.contains("double"))
                returnedCode += storeOperator + (varAdresse + 1) + "\n";
            returnedCode += storeOperator + varAdresse + "\n";
        }

        return returnedCode;
    }

    /**
    *   Cette fonction retourne les instructions nécessaires au bon calcul de l'indice du tableau
    *   auquel on veut accéder en fonction du type du tableau et en fonction de l'adresse du pointeur
    *   sur le tableau
    *
    *   @param arrayType Le type du tableau 'int', 'double' ou 'bool'/'booléen'
    *   @param indexExpression L'expression de l'indice du tableau auquel on veut stocker une valeur
    *   @param arrayPointerAdresse L'adresse du pointeur sur le tableau. 'arrayPointerAdresse' sera toujours négatif
    *   puisqu'on ne peut pas avoir accès à un pointeur sur tableau autre part que depuis une fonction
    *   et puisque dans une fonction, on aura donc une adresse négative
    *
    *   @return Retourne les instructions nécessaire pour avoir sur la pile les bons index en fonction
    *   du type du tableau 'arrayType'
    */
    private String pushArrayIndexesPointer(String arrayType, String indexExpression, Integer arrayPointerAdresse)
    {
        String returnedCode = "";

        boolean isDouble = arrayType.contains("double");
        //Si le tableau est un tableau de double, on va quand même devoir aller chercher le pointeur
        //sur le tableau au même endroit que si c'était un tableau de int, on va donc recalculer l'adresse
        //du pointeur en ajoutant 1 (on va se rapprocher de 0 puisque l'adresse est intiialement négative)
        int pointerAdresse = (arrayPointerAdresse + (isDouble ? 1 : 0));

        returnedCode += indexExpression;
        if(isDouble)//La variable est un double
        {
            returnedCode += "PUSHI 2\nMUL\n";//Le tableau est un tableau de double donc il faut
            //multiplier l'addresse des indices par 2
            returnedCode += "PUSHL " + pointerAdresse + "\nADD\n";//On ajoute la valeur du pointeur à l'index
            //pour que l'index puisse correctement pointer sur le tableau
            //Si on
            returnedCode += indexExpression;//On push un deuxième index car on va avoir 2 instructions STORER plus tard,
            //il nous faudra donc deux index
            returnedCode += "PUSHI 2\nMUL\n";
        }
        returnedCode += "PUSHL " + pointerAdresse + "\nADD\n";

        return returnedCode;
    }

    /**
    *   Cette fonction retourne les instructions nécessaires au bon calcul de l'indice du tableau
    *   auquel on veut accéder en fonction du type du tableau
    *
    *   @param arrayType Le type du tableau 'int', 'double' ou 'bool'/'booléen'
    *   @param indexExpression L'expression de l'indice du tableau auquel on veut stocker une valeur
    *
    *   @return Retourne les instructions nécessaire pour avoir sur la pile les bons index en fonction
    *   du type du tableau 'arrayType'
    */
    private String pushArrayIndexes(String arrayType, String indexExpression)
    {
        String returnedCode = "";

        returnedCode += indexExpression;
        if(arrayType.contains("double"))//La variable est un double
        {
            returnedCode += "PUSHI 2\nMUL\n";//Le tableau est un tableau de double donc il faut
            //multiplier l'addresse des indices par 2
            returnedCode += indexExpression;//On push un deuxième index car on va avoir 2 instructions STORER plus tard,
            //il nous faudra donc deux index
            returnedCode += "PUSHI 2\nMUL\n";
        }

        return returnedCode;
    }

    /**
    *   Retourne l'instruction qui, en fonction des arguments passés, pushera sur la
    *   pile la valeur de la variable désignée par 'varAdresse' et 'indexExpression'
    *
    *   @param varAdresse L'adresse de la variable à push sur la pile
    *   @param varType Le type de la variable: 'int', 'double' ou 'bool'/'boolean'
    *   @param indexExpression Si 'varAdresse' désigne l'adresse du début
    *   d'un tableau, 'indexExpression' désigne l'indice de ce tableau qui
    *   doit être push sur la pile. Peut être passé 'null' dans le cas ou on
    *   ne manipule pas de tableau
    *
    *   @return Retourne une ou plusieurs instructions permettant de push sur le haut
    *   de la pile la variable désignée par les arguments passés
    */
    private String pushVar(Integer varAdresse, String varType, String indexExpression)
    {
        String returnedCode = "";
        boolean isDouble = varType.contains("double");

        if(indexExpression != null)//On va agir sur un tableau
        {
                //Si on a un pointeur sur un tableau, on a pas besoin de varAdresse ici car
                //l'adresse du tableau a déjà été push dans les index du tableau, plus bas dans la stack
                //On va également traiter le tableau comme un 'int' puisque qu'on va en réalité traiter
                //l'adresse du tableau, pas le tableau lui même. L'adresse étant codé sur un octet, comme
                //un int, on considère que le tableau est un int

            /*
                PUSH INDEX
                MUL 2 IF DOUBLE
                ADD ADRESSE TABLEAU
                PUSHR 0

                PUSH INDEX
                MUL 2 IF DOUBLE
                ADD ADRESSE TABLEAU
                PUSHR 1
            */

            returnedCode += indexExpression;
            if(isDouble)//Si le tableau est un tableau de double, chaque indice du tableau prend deux emplacements
            //sur la pile donc on va devoir multiplier par deux l'index donné
                returnedCode += "PUSHI 2\nMUL\n";
            if(varAdresse < 0)
                returnedCode += "PUSHL " + (varAdresse + (isDouble ? 1 : 0)) + "\nADD\n";
            returnedCode += "PUSHR " + (varAdresse < 0 ? 0 : varAdresse) + "\n";

            if(isDouble)//Si la variable est de type double, on va devoir push la 'deuxième valeur'
            //du double
            {
                returnedCode += indexExpression;
                returnedCode += "PUSHI 2\nMUL\n";
                if(varAdresse < 0)
                    returnedCode += "PUSHL " + (varAdresse + (isDouble ? 1 : 0)) + "\nADD\n";
                returnedCode += "PUSHR " + (varAdresse < 0 ? 1 : (varAdresse + 1)) + "\n";
            }
        }
        else//On agit sur une variable simple
        {
            returnedCode += "PUSH" + (varAdresse < 0 ? "L" : "G") + " " + varAdresse + "\n";
            if(varType.contains("double"))//Si la variable est de type double, on va devoir push la 'deuxième valeur'
            //du double
                returnedCode += "PUSH" + (varAdresse < 0 ? "L" : "G") + " " + (varAdresse + 1) + "\n";
        }

        return returnedCode;
    }

    /**
    *   Cette fonction vérifie si un cast implicite doit être fait entre les deux expressions passées en paramètre.
    *   Si un cast doit être fait, cette fonction fait le cast et retourne le code associé.
    *
    *   @param expressionLCode Le code de l'expression gauche
    *   @param expressionLType Le type de l'expression gauche
    *   @param expressionRCode Le code de l'expression droite
    *   @param expressionRType Le type de l'expression droite
    *
    *   @return Si un cast implicite doit être fait, cette fonction retourne le code contenant les deux
    *   expressions ainsi que le cast implicit (placé au bon endroit pour caster la bonne expression)
    */
    private String expressionImplicitCast(String expressionLCode, String expressionLType, String expressionRCode, String expressionRType)
    {
        String returnedCode = "";

        //Expression gauche est bool, expression droite ne l'est pas
        if(expressionLType.contains("bool") && !(expressionRType.contains("bool")))
            throw new Error("ERROR: You cannot operate on boolean and " + expressionRType);
        //Expression droite est bool, expression gauche ne l'est pas
        else if(expressionRType.contains("bool") && !(expressionLType.contains("bool")))
            throw new Error("ERROR: You cannot operate on boolean and " + expressionLType);
        //On va cast l'expression droite en double
        else if(expressionLType.contains("double") && expressionRType.contains("int"))
        {
            System.err.println("WARNING: Operating on two different types : " + String.format("%s and %s", expressionLType, expressionRType));

            returnedCode += expressionLCode + expressionRCode + "ITOF\n";
        }
        //On va cast l'expression gauche en double
        else if(expressionLType.contains("int") && expressionRType.contains("double"))
        {
            System.err.println("WARNING: Operating on two different types : " + String.format("%s and %s", expressionLType, expressionRType));

            returnedCode += expressionLCode + "ITOF\n" + expressionRCode;
        }

        return returnedCode;
    }
}

start
    :
        calcul EOF { System.out.println($calcul.code); }
    ;

calcul returns [ String code ]
@init{ $code = new String(); }   // On initialise code, pour ensuite l'utiliser comme accumulateur
    :
        (declaration { $code += $declaration.code; })*
        NEWLINE*

        { $code += "JUMP Main\n"; }

        (fonction { $code += $fonction.code; })*
        NEWLINE*

        { $code += "LABEL Main\n"; }
        (instruction { $code += $instruction.code; })*
        NEWLINE*

        { $code += "HALT\n"; }
    ;

condition returns [ String code ]
@init { $code = new String(); }
    :
    '('condition')'
    {
        $code = $condition.code;
    }
    | '!'condition
    {
      $code =  $condition.code;

      $code += "PUSHI 1\n";
      $code += "NEQ\n";
    }

    | expressionL=expression op=('=='|'!='|'<>') expressionR=expression
    {
        boolean isDouble = $expressionL.type.contains("double") || $expressionR.type.contains("double");
        String opCode = (isDouble ? "F" : "") + ($op.text.equals("==") ? "EQUAL" : "NEQ");

        $code += expressionImplicitCast($expressionL.code, $expressionL.type, $expressionR.code, $expressionR.type);
        if($code.equals(""))//Il n'y a pas eu d'implicit cast
            $code = $expressionL.code + $expressionR.code;//On rajoute les deux expressions sans cast

        $code += opCode + "\n";
    }
    | expressionL=expression op=('<'|'<='|'>'|'>=') expressionR=expression
    {
        boolean isDouble = $expressionL.type.contains("double") || $expressionR.type.contains("double");
        String opCode = (isDouble ? "F" : "") + ($op.text.equals("<") ? "INF" : $op.text.equals("<=") ? "INFEQ" : $op.text.equals(">") ? "SUP" : "SUPEQ");

        $code += expressionImplicitCast($expressionL.code, $expressionL.type, $expressionR.code, $expressionR.type);
        if($code.equals(""))//Il n'y a pas eu d'implicit cast
            $code = $expressionL.code + $expressionR.code;//On rajoute les deux expressions sans cast

        $code += opCode + "\n";

    }
    | conditionL=condition '&&' conditionR=condition
    {
        $code =  $conditionL.code + $conditionR.code;
        $code += "ADD\n";
        $code += "PUSHI 2\n";
        $code += "EQUAL\n";
    }
    | conditionL=condition '||' conditionR=condition
    {
        $code =  $conditionL.code + $conditionR.code;
        $code += "ADD\n";
        $code += "PUSHI 1\n";
        $code += "SUPEQ\n";
    }
    | expression
    {
        $code = $expression.code;
        $code += "PUSHI 0\n";
        $code += "NEQ\n";
    }
    | TRUE      {$code = "PUSHI 1\n";}
    | FALSE     {$code = "PUSHI 0\n";}
    ;

declaration returns [ String code ]
@init { $code = new String(); }
    :
        'var' IDENTIFIANT ':' TYPE finInstruction
        {
            String type = $TYPE.text;

            if(type.equals("int"))
                $code = "PUSHI 0\n";//Reservation d'un espace sur la pile
            else if(type.equals("double"))
                $code = "PUSHF 0.0\n";
            else if(type.equals("bool") || type.equals("boolean"))
            {
                type = "bool";
                $code = "PUSHI 0\n";//Reservation d'un espace sur la pile
            }
            else
                throw new Error("ERROR: Unrecognized type for variable declaration : var " + $IDENTIFIANT.text + " : " + $TYPE.text);

            tablesSymboles.putVar($IDENTIFIANT.text, type);
        }
        | 'var' IDENTIFIANT ':' TYPE '=' expression finInstruction
        {
            boolean isDouble = $TYPE.text.equals("double");
            boolean castDouble = false;

            $code = $expression.code;

            if(isDouble && $expression.type.contains("int"))
            {
                System.err.println("WARNING: Assigning an integer to a double variable for variable " + $IDENTIFIANT.text);
                castDouble = true;
            }
            else if(($TYPE.text.equals("boolean") || $TYPE.text.contains("bool")) && !($expression.type.contains("bool")))
            //La variable est un booléen mais l'expression sur la droite n'est pas un booléen
                throw new Error("ERROR: You cannot assign a non-boolean expression to a boolean variable.");
            else if(!isDouble && $expression.type.contains("double"))
                throw new Error("ERROR: Assigning a double to the non-double variable " + $IDENTIFIANT.text);

            if(castDouble)
                $code += "ITOF\n";

            tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
        }
        | 'var' IDENTIFIANT ':' TYPE '['ENTIER']' finInstruction
        {
            int size = Integer.parseInt($ENTIER.text);
            boolean isDouble = $TYPE.text.equals("double");
            String type = $TYPE.text;

            if(type.equals("double") && !doubleRegisterAllocated)//Si c'est un tableau de double et
            //que le registre réservé n'a pas encore été alloué
            {
                tablesSymboles.putVar(RESERVED_DOUBLE_REGISTER_LABEL, "int");
                doubleRegisterAllocated = true;//Le registre a été alloué

                $code += "PUSHI 0\n";//On push un octet pour allouer l'espace du registre sur la pile
            }
            else if(type.equals("boolean"))
                type = "bool";

            $code += "ALLOC " + (size * (isDouble ? 2 : 1)) + "\n";//On multiplie par deux la taille de l'allocation pour les doubles
            tablesSymboles.putVar($IDENTIFIANT.text, "array" + type);
            for(int i = 1; i < size; i++)//On va réserver un emplacement dans la table des symboles pour chaque
            //indice du tableau que l'on vient de déclarer
                tablesSymboles.putVar($IDENTIFIANT.text + "[" + i + "]", type);
        }
    ;

assignation returns [ String code ]
@init { $code = new String(); }
    :
        IDENTIFIANT ('['index=expression']')? '++'
        {
            AdresseType adresseType = tablesSymboles.getAdresseType($IDENTIFIANT.text);
            boolean isDouble = adresseType.type.contains("double");
            boolean isArray = adresseType.type.contains("array");
            printErrorIfBool(adresseType.type, "++");//Emits an error if IDENTIFIANT is of type
            //boolean as we cannot '++' a boolean

            if(isArray && $index.text == null)//La variable d'un tableau a été donnée mais aucun index n'a été donné
                throw new Error("ERROR: Operator '++' cannot be applied to an array.");
            else if(isArray)//Un index a été donné
            {
                checkArrayIndexType($index.type);
                if(adresseType.adresse < 0)//On utilise ici un pointeur sur le début du tableau
                    $code += pushArrayIndexesPointer(adresseType.type, $index.code, adresseType.adresse);
                else
                    $code += pushArrayIndexes(adresseType.type, $index.code);
            }

            //On push 1 pour ajouter 1 avec '++'
            $code += "PUSH" + (isDouble ? "F" : "I") + " 1" + (isDouble ? ".0" : "") + "\n";
            $code += pushVar(adresseType.adresse, adresseType.type, (isArray ? $index.code : null));
            $code += (isDouble ? "F" : "") + "ADD" + "\n";
            $code += storeVar(adresseType.adresse, adresseType.type);
        }
        | IDENTIFIANT ('['index=expression']')? '--'
        {
            AdresseType adresseType = tablesSymboles.getAdresseType($IDENTIFIANT.text);
            boolean isDouble = adresseType.type.contains("double");
            boolean isArray = adresseType.type.contains("array");
            printErrorIfBool(adresseType.type, "--");//Emits an error if IDENTIFIANT is of type
            //boolean as we cannot '--' a boolean

            if(isArray && $index.text == null)//La variable d'un tableau a été donnée mais aucun index n'a été donné
                throw new Error("ERROR: Operator '--' cannot be applied to an array.");
            else if(isArray)//Un index a été spécifié
            {
                checkArrayIndexType($index.type);
                if(adresseType.adresse < 0)//On utilise ici un pointeur sur le début du tableau
                    $code += pushArrayIndexesPointer(adresseType.type, $index.code, adresseType.adresse);
                else
                    $code += pushArrayIndexes(adresseType.type, $index.code);
            }

            $code += pushVar(adresseType.adresse, adresseType.type, (isArray ? $index.code : null));
            $code += "PUSH" + (isDouble ? "F" : "I") + " 1" + (isDouble ? ".0" : "") + "\n";
            $code += (isDouble ? "F" : "") + "SUB" + "\n";
            $code += storeVar(adresseType.adresse, adresseType.type);
        }
        | IDENTIFIANT ('['index=expression']')? op=('='|'+='|'-='|'*='|'/=') expression
        {
            AdresseType adresseType = tablesSymboles.getAdresseType($IDENTIFIANT.text);
            Integer adresse = adresseType.adresse;
            String type = adresseType.type;
            boolean isArray = adresseType.type.contains("array");

            if(!$op.text.equals("="))
            {
                //Emits an error if IDENTIFIANT is of type
                //boolean as we cannot use '+=', '-=', '*=' or '/=' on a boolean
                printErrorIfBool(type, $op.text);
                printErrorIfBool($expression.type, $op.text);//Same for expression
            }
            //Checking that we're using the '=' between two booleans
            else if($expression.type.contains("bool") && !type.contains("bool"))
                throw new Error("ERROR: You cannot assign a boolean to a variable of type '" + type + "'");
            else if(!$expression.type.contains("bool") && type.contains("bool"))
                throw new Error("ERROR: You cannot assign a non-boolean value to a boolean variable");

            if(isArray && $index.text == null)//La variable d'un tableau a été donnée mais aucun index n'a été donné
                throw new Error("ERROR: Operator '" + $op.text + "' cannot be applied to an array.");
            else if(isArray)//Un index a été spécifié, on veut donc assigner à une case d'un tableau
            {
                checkArrayIndexType($index.type);
                if(adresse < 0)//La variable est un tableau mais nous avons une adresse négative, nous avons
                //donc accès ici à un pointeur sur le début du tableau et non pas le tableau directement
                    $code += pushArrayIndexesPointer(type, $index.code, adresse);
                else
                    $code += pushArrayIndexes(type, $index.code);//On va push les index pour préparer les futures
                //instructions STORER
            }

            if($op.text.equals("-=") || $op.text.equals("/="))//Si on a '-=' ou '/=', on va d'abord devoir
            //push la variable et ensuite 'expression'
                $code += pushVar(adresse, type, (isArray ? $index.code : null));
            else
            {
                $code += $expression.code;
                if(type.contains("double") && $expression.type.contains("int"))//Si la variable à laquelle on est en
                //train d'assigner une nouvelle valeur est un double et que la valeur qu'on essaie d'assigner
                //est un int, on va cast le int en float
                {
                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, $op.text, $expression.type));
                    $code += "ITOF\n";
                }
                else if(type.contains("int") && $expression.type.contains("double"))//Vice-versa
                {
                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, $op.text, $expression.type));
                    $code += "FTOI\n";
                }
            }

            if($op.text.equals("="))
                $code += storeVar(adresse, type);
            else if($op.text.equals("+="))
            {
                $code += pushVar(adresse, type, (isArray ? $index.code : null));

                $code += (type.contains("double") ? "F" : "") + "ADD\n";
                $code += storeVar(adresse, type);
            }
            else if($op.text.equals("-="))
            {
                $code += $expression.code;
                if(type.contains("double") && $expression.type.contains("int"))//Si la variable à laquelle on est en
                //train d'assigner une nouvelle valeur est un float et que la valeur qu'on essaie d'assigner
                //est un int, on va cast le int en float
                {
                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, $op.text, $expression.type));
                    $code += "ITOF\n";
                }

                $code += (type.contains("double") ? "F" : "") + "SUB\n";
                $code += storeVar(adresse, type);
            }
            else if($op.text.equals("*="))
            {
                $code += pushVar(adresse, type, (isArray ? $index.code : null));

                $code += (type.contains("double") ? "F" : "") + "MUL\n";
                $code += storeVar(adresse, type);
            }
            else if($op.text.equals("/="))
            {
                $code += $expression.code;
                if(type.contains("double") && $expression.type.contains("int"))//Si la variable à laquelle on est en
                //train d'assigner une nouvelle valeur est un float et que la valeur qu'on essaie d'assigner
                //est un int, on va cast le int en float
                {
                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, $op.text, $expression.type));
                    $code += "ITOF\n";
                }

                $code += (type.contains("double") ? "F" : "") + "DIV\n";
                $code += storeVar(adresse, type);
            }
        }
    ;

whileStruct returns [ String code ]
  :
  WHILE '('condition')' NEWLINE* instructionOrBloc NEWLINE*
  {
      String labelStart = getNewLabel();
      String labelEnd   = getNewLabel();

      $code  = "LABEL " + labelStart + "\n";
      $code += $condition.code;
      $code += "JUMPF " + labelEnd + "\n";
      $code += $instructionOrBloc.code;
      $code += "JUMP " + labelStart + "\n";
      $code += "LABEL " + labelEnd + "\n";
  }
  ;

ifStruct returns [ String code ]
  :
  IF '('condition')' NEWLINE* ifIns=instructionOrBloc NEWLINE* ELSE NEWLINE* elseIns=instructionOrBloc NEWLINE*
  {
    String elseLabel = getNewLabel();
    String endLabel = getNewLabel();

    $code = $condition.code;
    $code += "JUMPF " + elseLabel + "\n";
    $code += $ifIns.code;
    $code += "JUMP " + endLabel + "\n";//Pour ne pas exécuter le code de la branche 'else''
    $code += "LABEL " + elseLabel + "\n";
    $code += $elseIns.code;
    $code += "LABEL " + endLabel + "\n";
  }
  |
  IF '('condition')' NEWLINE* instructionOrBloc NEWLINE*
  {
      String labelEnd = getNewLabel();

      $code = $condition.code;
      $code += "JUMPF " + labelEnd + "\n";
      $code += $instructionOrBloc.code;
      $code += "LABEL " + labelEnd + "\n";
  }
  ;

forStruct returns [ String code ]
  :
      FOR '('startAssign=assignation ';' condition ';' endAssign=assignation ')' NEWLINE* instructionOrBloc NEWLINE*
      {
          String labelStart = getNewLabel();
          String labelEnd = getNewLabel();

          $code = $startAssign.code;
          $code += "LABEL " + labelStart + "\n";
          $code += $condition.code;
          $code += "JUMPF " + labelEnd + "\n";
          $code += $instructionOrBloc.code;
          $code += $endAssign.code;
          $code += "JUMP " + labelStart + "\n";
          $code += "LABEL " + labelEnd + "\n";
      }
  ;

repeatUntilStruct returns [ String code ]
    :
        REPEAT NEWLINE* instructionOrBloc NEWLINE* UNTIL '('condition')' finInstruction NEWLINE*
        {
            String labelStart = getNewLabel();

            $code = "LABEL " + labelStart + "\n";
            $code += $instructionOrBloc.code;
            $code += $condition.code;
            $code += "JUMPF " + labelStart + "\n";
        }
    ;

structure returns [String code]
  :
    whileStruct         { $code = $whileStruct.code;  }
  | ifStruct            { $code = $ifStruct.code;     }
  | forStruct           { $code = $forStruct.code;    }
  | repeatUntilStruct   { $code = $repeatUntilStruct.code; }
  ;

instruction returns [ String code ]
    :
          structure                                 { $code = $structure.code;  }
        | expression finInstruction                 { $code = $expression.code; }
        | assignation finInstruction                { $code = $assignation.code;}
        | ';'                                       { $code = ""; } //Empty instruction
        | 'read'  '('IDENTIFIANT')' finInstruction
        {
            AdresseType adrType = tablesSymboles.getAdresseType($IDENTIFIANT.text);

            if(adrType.type.contains("double"))
            {
                $code =  "READF\n";
                $code += storeVar(adrType.adresse, "double");
            }
            else
                $code = "READ\n" + "STOREG " + tablesSymboles.getAdresseType($IDENTIFIANT.text).adresse + "\n";
        }
        | 'write' '('expression')'  finInstruction
        {
            boolean isDouble = $expression.type.contains("double");

            $code = $expression.code + "WRITE" + (isDouble ? "F" : "") + "\n";
            if(isDouble)
                $code += "POP\nPOP\n";
            else
                $code += "POP\n";
        }
        | RETURN  expression finInstruction
        {
            $code = "";

            if(tablesSymboles.getAdresseType("returnVar") != null)//Si la fonction n'est pas de type void et que "returnVar"
            //a été déclarée
                $code += $expression.code + storeVar(tablesSymboles.getAdresseType("returnVar").adresse, $expression.type);

            $code += "RETURN\n";
        }
    ;

bloc returns [ String code ]
@init { $code = new String(); }
    :
        '{' NEWLINE* (instruction { $code += $instruction.code; })* NEWLINE* '}'
    ;

instructionOrBloc returns [ String code ]
  :
      instruction   { $code = $instruction.code; }
    | bloc          { $code = $bloc.code; }
  ;

fonction returns [ String code ]

@init{ tablesSymboles.newTableLocale(); $code = new String(); } // instancier la table locale
@after{ tablesSymboles.dropTableLocale(); } // détruire la table locale
    : 'fun' IDENTIFIANT ':' type=(TYPE | 'void')
        {
            tablesSymboles.newFunction($IDENTIFIANT.text, $type.text);//On ajoute la fonction à la table des symboles
            if(!$type.text.equals("void"))
                tablesSymboles.putVar("returnVar", $type.text);

            $code += "LABEL " + $IDENTIFIANT.text + "\n";
    	}
        '('  params ? ')' NEWLINE* bloc NEWLINE*
        {
            $code += $bloc.code;
            $code += "RETURN\n";//Safety return
        }
    ;

params
    : (TYPE IDENTIFIANT
    {
        tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
    }
    | TYPE IDENTIFIANT '['']'
    {
        tablesSymboles.putVar($IDENTIFIANT.text, "array" + $TYPE.text);
    })
    (
        (',' TYPE IDENTIFIANT
        {
            tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
        })
        |
        ( ',' TYPE IDENTIFIANT '['']'
        {
            tablesSymboles.putVar($IDENTIFIANT.text, "array" + $TYPE.text);//On réserve un octet pour l'adresse du tableau
        })
    )*
    ;

 // init nécessaire à cause du ? final et donc args peut être vide (mais $args sera non null)
args returns [ String code, int size ]
@init{ $code = new String(); $size = 0; }
    : ( expression
        {
            $code += $expression.code;

            //Si on passe un tableau à la fonction, on ne va passer que le pointeur donc c'est de taille 1
            //Sinon, si c'est un double, taille 2, sinon taille 1
            $size += ($expression.type.contains("array") ? 1 : $expression.type.equals("double") ? 2 : 1);
        }
        ( ',' expression
        {
            $code += $expression.code;
            $size += ($expression.type.contains("array") ? 1 : $expression.type.equals("double") ? 2 : 1);
        }
        )*
      )?
    ;

expression returns [ String code, String type ]
@init { $code = new String(); }
    : '('expression')'  {$code = $expression.code; $type = $expression.type;}
    | '-'expression
    {
        //If expression is a boolean expression, throw an error
        if($expression.type.contains("bool") || $expression.type.contains("bool"))
            throw new Error("ERROR: The unary operator '-' is not supported for the boolean type");
        boolean isDouble = $expression.type.contains("double");

        $code += "PUSH" + (isDouble ? "F" : "I") + " 0" + (isDouble ? ".0" : "") + "\n";
        $code += $expression.code;
        $code += (isDouble ? "F" : "") + "SUB" + "\n";

        $type = $expression.type;
    }
    | a=expression op=('*'|'/') b=expression
    {
        printErrorIfBool($a.type, $op.text);//Emits an error if a is a boolean expression as we
        //cannot multiply or divide a boolean
        printErrorIfBool($b.type, $op.text);//Same for b

        //On ne peut pas avoir de bool à partir d'ici
        if(!equalsType($a.type, $b.type))
        {
            $code = expressionImplicitCast($a.code, $a.type, $b.code, $b.type);
            $code += ($op.text.equals("*") ? "FMUL" : "FDIV") + "\n";

            $type = "double";
        }
        else
        {
            $code = $a.code + $b.code + ($a.type.contains("double") ? "F" : "") + ($op.text.equals("*") ? "MUL" : "DIV") + "\n";

            $type = $a.type;//= $b.type donnerait la même chose puisque a.type = b.type ici
        }
    }
    | a=expression op=('+'|'-') b=expression
    {
        printErrorIfBool($a.type, $op.text);//Emits an error if a is a boolean expression as we
        //cannot add or substract a boolean
        printErrorIfBool($b.type, $op.text);//Same for b

        if(!equalsType($a.type, $b.type))
        {
            $code = expressionImplicitCast($a.code, $a.type, $b.code, $b.type);
            $code += ($op.text.equals("+") ? "FADD" : "FSUB") + "\n";

            $type = "double";
        }
        else
        {
            $code = $a.code + $b.code + ($a.type.contains("double") ? "F" : "") + ($op.text.equals("+") ? "ADD" : "SUB") + "\n";

            $type = $a.type;//= $b.type donnerait la même chose puisque a.type = b.type ici
        }
    }
    | '('TYPE')' expression
    {
        $code = $expression.code;

        if($expression.type.contains("double") && $TYPE.text.equals("int"))//Cast explicite de double en int
        {
            $code += "FTOI\n";
            $type = "int";
        }
        else if(!$expression.type.contains("double") && $TYPE.text.equals("double"))//Cast explicite de bool ou int en double
        {
            $code += "ITOF\n";
            $type = "double";
        }
        else if($TYPE.text.equals("bool") || $TYPE.text.equals("boolean"))//Cast d'une valeur en bool
        {
            if($expression.type.contains("double"))//L'expression à caster est un double, on va regarder si c'est différent de 0.0 ou pas
            {
                $code += "PUSHF 0.0\n";
                $code += "FNEQ\n";
            }
            else if($expression.type.contains("int"))//L'expression à caster en bool est un int, on va regarder si c'est différent de 0 ou pas
            {
                $code += "PUSHI 0\n";
                $code += "NEQ\n";
            }

            $type = "bool";
        }
        else//Cast du même type, rien à faire
        {
            $code = $expression.code;

            $type = $expression.text;
        }
    }
    | nb=(ENTIER | REEL)
    {
        boolean isDouble = $nb.text.contains(".");

        $code = "PUSH" + (isDouble ? "F" : "I") + " " + $nb.text + "\n";
        $type = (isDouble ? "double" : "int");
    }
    | IDENTIFIANT ('['index=expression']')?
    {
        AdresseType adrType = tablesSymboles.getAdresseType($IDENTIFIANT.text);

        boolean isArray = adrType.type.contains("array");

        if(isArray && $index.text != null)
            checkArrayIndexType($index.type);
        if(!isArray & $index.text != null)//On essayer d'indexer une variable qui n'est pas un tableau
            throw new Error("ERROR: You cannot index the non-array variable '" + $IDENTIFIANT.text + "'");

        if(isArray & $index.text == null)//Seulement le non d'un tableau (sans indexing) a été précisé
            $code = "PUSHI " + adrType.adresse + "\n";//La variable est un tableau, on push l'adresse du tableau
        else//On push la valeur de la variable
            $code = pushVar(adrType.adresse, adrType.type, (isArray ? $index.code : null));

        $type = adrType.type;
    }
    | IDENTIFIANT '(' args ')'
    {
        String typeFonction = tablesSymboles.getFunction($IDENTIFIANT.text);
        if(typeFonction == null)//La fonction n'est pas définie
            return null;

        $code = "";
        if(!typeFonction.equals("void"))
        {
            boolean isFunctionDouble = typeFonction.equals("double");
            $code = "PUSH" + (isFunctionDouble ? "F" : "I") + " 0" + (isFunctionDouble ? ".0" : "") + "\n";//Valeur de retour
        }
        $code += $args.code;//On push les arguments sur la stack

        $code += "CALL " + $IDENTIFIANT.text + "\n";
        for(int i = 0; i < $args.size; i++)//POP de tous les arguments précédemment passés
            $code += "POP\n";

        $type = typeFonction;
    }
    | 'true'    { $code = "PUSHI 1\n"; $type = "bool"; }
    | 'false'   { $code = "PUSHI 0\n"; $type = "bool"; }
    ;

finInstruction : ( NEWLINE | ';' )+ ;

fragment BOOLEAN: 'bool' | 'boolean';
TYPE: 'int' | 'double' | BOOLEAN;

WHILE: 'while';
FOR: 'for';
REPEAT: 'repeat';
UNTIL: 'until';
IF: 'if';
ELSE: 'else';

RETURN: 'return';
TRUE: 'true';
FALSE: 'false';

IDENTIFIANT: ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;

MONO_COMMENT: ('%' | '#' | '//') ~[\r\n]* -> skip;
MULTI_COMMENT: '/*' .*? '*/' -> skip;

NEWLINE: '\r'?'\n';

ENTIER: ('0'..'9')('0'..'9')*;
REEL: ('0'..'9')+'.'('0'..'9')*;

UNMATCH: . -> skip;
