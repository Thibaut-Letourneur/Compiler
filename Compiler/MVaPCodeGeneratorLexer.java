// Generated from MVaPCodeGenerator.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MVaPCodeGeneratorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, TYPE=36, WHILE=37, FOR=38, REPEAT=39, 
		UNTIL=40, IF=41, ELSE=42, RETURN=43, TRUE=44, FALSE=45, IDENTIFIANT=46, 
		MONO_COMMENT=47, MULTI_COMMENT=48, NEWLINE=49, ENTIER=50, REEL=51, UNMATCH=52;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "BOOLEAN", "TYPE", "WHILE", "FOR", "REPEAT", "UNTIL", 
			"IF", "ELSE", "RETURN", "TRUE", "FALSE", "IDENTIFIANT", "MONO_COMMENT", 
			"MULTI_COMMENT", "NEWLINE", "ENTIER", "REEL", "UNMATCH"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'!'", "'=='", "'!='", "'<>'", "'<'", "'<='", "'>'", 
			"'>='", "'&&'", "'||'", "'var'", "':'", "'='", "'['", "']'", "'++'", 
			"'--'", "'+='", "'-='", "'*='", "'/='", "';'", "'read'", "'write'", "'{'", 
			"'}'", "'fun'", "'void'", "','", "'-'", "'*'", "'/'", "'+'", null, "'while'", 
			"'for'", "'repeat'", "'until'", "'if'", "'else'", "'return'", "'true'", 
			"'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"TYPE", "WHILE", "FOR", "REPEAT", "UNTIL", "IF", "ELSE", "RETURN", "TRUE", 
			"FALSE", "IDENTIFIANT", "MONO_COMMENT", "MULTI_COMMENT", "NEWLINE", "ENTIER", 
			"REEL", "UNMATCH"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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


	public MVaPCodeGeneratorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MVaPCodeGenerator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0156\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\3%\3%\5%\u00da\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u00e6\n&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*"+
		"\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/"+
		"\3/\3/\3/\3\60\3\60\7\60\u011b\n\60\f\60\16\60\u011e\13\60\3\61\3\61\3"+
		"\61\5\61\u0123\n\61\3\61\7\61\u0126\n\61\f\61\16\61\u0129\13\61\3\61\3"+
		"\61\3\62\3\62\3\62\3\62\7\62\u0131\n\62\f\62\16\62\u0134\13\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\63\5\63\u013c\n\63\3\63\3\63\3\64\3\64\7\64\u0142"+
		"\n\64\f\64\16\64\u0145\13\64\3\65\6\65\u0148\n\65\r\65\16\65\u0149\3\65"+
		"\3\65\7\65\u014e\n\65\f\65\16\65\u0151\13\65\3\66\3\66\3\66\3\66\3\u0132"+
		"\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I\2K&M\'O(Q)S*U+W,Y-[.]/_\60a\61c\62e\63g\64i\65k\66"+
		"\3\2\6\5\2C\\aac|\6\2\62;C\\aac|\4\2%%\'\'\4\2\f\f\17\17\2\u015f\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5o\3\2\2\2\7q"+
		"\3\2\2\2\ts\3\2\2\2\13v\3\2\2\2\ry\3\2\2\2\17|\3\2\2\2\21~\3\2\2\2\23"+
		"\u0081\3\2\2\2\25\u0083\3\2\2\2\27\u0086\3\2\2\2\31\u0089\3\2\2\2\33\u008c"+
		"\3\2\2\2\35\u0090\3\2\2\2\37\u0092\3\2\2\2!\u0094\3\2\2\2#\u0096\3\2\2"+
		"\2%\u0098\3\2\2\2\'\u009b\3\2\2\2)\u009e\3\2\2\2+\u00a1\3\2\2\2-\u00a4"+
		"\3\2\2\2/\u00a7\3\2\2\2\61\u00aa\3\2\2\2\63\u00ac\3\2\2\2\65\u00b1\3\2"+
		"\2\2\67\u00b7\3\2\2\29\u00b9\3\2\2\2;\u00bb\3\2\2\2=\u00bf\3\2\2\2?\u00c4"+
		"\3\2\2\2A\u00c6\3\2\2\2C\u00c8\3\2\2\2E\u00ca\3\2\2\2G\u00cc\3\2\2\2I"+
		"\u00d9\3\2\2\2K\u00e5\3\2\2\2M\u00e7\3\2\2\2O\u00ed\3\2\2\2Q\u00f1\3\2"+
		"\2\2S\u00f8\3\2\2\2U\u00fe\3\2\2\2W\u0101\3\2\2\2Y\u0106\3\2\2\2[\u010d"+
		"\3\2\2\2]\u0112\3\2\2\2_\u0118\3\2\2\2a\u0122\3\2\2\2c\u012c\3\2\2\2e"+
		"\u013b\3\2\2\2g\u013f\3\2\2\2i\u0147\3\2\2\2k\u0152\3\2\2\2mn\7*\2\2n"+
		"\4\3\2\2\2op\7+\2\2p\6\3\2\2\2qr\7#\2\2r\b\3\2\2\2st\7?\2\2tu\7?\2\2u"+
		"\n\3\2\2\2vw\7#\2\2wx\7?\2\2x\f\3\2\2\2yz\7>\2\2z{\7@\2\2{\16\3\2\2\2"+
		"|}\7>\2\2}\20\3\2\2\2~\177\7>\2\2\177\u0080\7?\2\2\u0080\22\3\2\2\2\u0081"+
		"\u0082\7@\2\2\u0082\24\3\2\2\2\u0083\u0084\7@\2\2\u0084\u0085\7?\2\2\u0085"+
		"\26\3\2\2\2\u0086\u0087\7(\2\2\u0087\u0088\7(\2\2\u0088\30\3\2\2\2\u0089"+
		"\u008a\7~\2\2\u008a\u008b\7~\2\2\u008b\32\3\2\2\2\u008c\u008d\7x\2\2\u008d"+
		"\u008e\7c\2\2\u008e\u008f\7t\2\2\u008f\34\3\2\2\2\u0090\u0091\7<\2\2\u0091"+
		"\36\3\2\2\2\u0092\u0093\7?\2\2\u0093 \3\2\2\2\u0094\u0095\7]\2\2\u0095"+
		"\"\3\2\2\2\u0096\u0097\7_\2\2\u0097$\3\2\2\2\u0098\u0099\7-\2\2\u0099"+
		"\u009a\7-\2\2\u009a&\3\2\2\2\u009b\u009c\7/\2\2\u009c\u009d\7/\2\2\u009d"+
		"(\3\2\2\2\u009e\u009f\7-\2\2\u009f\u00a0\7?\2\2\u00a0*\3\2\2\2\u00a1\u00a2"+
		"\7/\2\2\u00a2\u00a3\7?\2\2\u00a3,\3\2\2\2\u00a4\u00a5\7,\2\2\u00a5\u00a6"+
		"\7?\2\2\u00a6.\3\2\2\2\u00a7\u00a8\7\61\2\2\u00a8\u00a9\7?\2\2\u00a9\60"+
		"\3\2\2\2\u00aa\u00ab\7=\2\2\u00ab\62\3\2\2\2\u00ac\u00ad\7t\2\2\u00ad"+
		"\u00ae\7g\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7f\2\2\u00b0\64\3\2\2\2\u00b1"+
		"\u00b2\7y\2\2\u00b2\u00b3\7t\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7v\2\2"+
		"\u00b5\u00b6\7g\2\2\u00b6\66\3\2\2\2\u00b7\u00b8\7}\2\2\u00b88\3\2\2\2"+
		"\u00b9\u00ba\7\177\2\2\u00ba:\3\2\2\2\u00bb\u00bc\7h\2\2\u00bc\u00bd\7"+
		"w\2\2\u00bd\u00be\7p\2\2\u00be<\3\2\2\2\u00bf\u00c0\7x\2\2\u00c0\u00c1"+
		"\7q\2\2\u00c1\u00c2\7k\2\2\u00c2\u00c3\7f\2\2\u00c3>\3\2\2\2\u00c4\u00c5"+
		"\7.\2\2\u00c5@\3\2\2\2\u00c6\u00c7\7/\2\2\u00c7B\3\2\2\2\u00c8\u00c9\7"+
		",\2\2\u00c9D\3\2\2\2\u00ca\u00cb\7\61\2\2\u00cbF\3\2\2\2\u00cc\u00cd\7"+
		"-\2\2\u00cdH\3\2\2\2\u00ce\u00cf\7d\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d1"+
		"\7q\2\2\u00d1\u00da\7n\2\2\u00d2\u00d3\7d\2\2\u00d3\u00d4\7q\2\2\u00d4"+
		"\u00d5\7q\2\2\u00d5\u00d6\7n\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7c\2\2"+
		"\u00d8\u00da\7p\2\2\u00d9\u00ce\3\2\2\2\u00d9\u00d2\3\2\2\2\u00daJ\3\2"+
		"\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7p\2\2\u00dd\u00e6\7v\2\2\u00de\u00df"+
		"\7f\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1\7w\2\2\u00e1\u00e2\7d\2\2\u00e2"+
		"\u00e3\7n\2\2\u00e3\u00e6\7g\2\2\u00e4\u00e6\5I%\2\u00e5\u00db\3\2\2\2"+
		"\u00e5\u00de\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6L\3\2\2\2\u00e7\u00e8\7"+
		"y\2\2\u00e8\u00e9\7j\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7n\2\2\u00eb\u00ec"+
		"\7g\2\2\u00ecN\3\2\2\2\u00ed\u00ee\7h\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0"+
		"\7t\2\2\u00f0P\3\2\2\2\u00f1\u00f2\7t\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4"+
		"\7r\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7v\2\2\u00f7"+
		"R\3\2\2\2\u00f8\u00f9\7w\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7v\2\2\u00fb"+
		"\u00fc\7k\2\2\u00fc\u00fd\7n\2\2\u00fdT\3\2\2\2\u00fe\u00ff\7k\2\2\u00ff"+
		"\u0100\7h\2\2\u0100V\3\2\2\2\u0101\u0102\7g\2\2\u0102\u0103\7n\2\2\u0103"+
		"\u0104\7u\2\2\u0104\u0105\7g\2\2\u0105X\3\2\2\2\u0106\u0107\7t\2\2\u0107"+
		"\u0108\7g\2\2\u0108\u0109\7v\2\2\u0109\u010a\7w\2\2\u010a\u010b\7t\2\2"+
		"\u010b\u010c\7p\2\2\u010cZ\3\2\2\2\u010d\u010e\7v\2\2\u010e\u010f\7t\2"+
		"\2\u010f\u0110\7w\2\2\u0110\u0111\7g\2\2\u0111\\\3\2\2\2\u0112\u0113\7"+
		"h\2\2\u0113\u0114\7c\2\2\u0114\u0115\7n\2\2\u0115\u0116\7u\2\2\u0116\u0117"+
		"\7g\2\2\u0117^\3\2\2\2\u0118\u011c\t\2\2\2\u0119\u011b\t\3\2\2\u011a\u0119"+
		"\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"`\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0123\t\4\2\2\u0120\u0121\7\61\2\2"+
		"\u0121\u0123\7\61\2\2\u0122\u011f\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0127"+
		"\3\2\2\2\u0124\u0126\n\5\2\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u012a\3\2\2\2\u0129\u0127\3\2"+
		"\2\2\u012a\u012b\b\61\2\2\u012bb\3\2\2\2\u012c\u012d\7\61\2\2\u012d\u012e"+
		"\7,\2\2\u012e\u0132\3\2\2\2\u012f\u0131\13\2\2\2\u0130\u012f\3\2\2\2\u0131"+
		"\u0134\3\2\2\2\u0132\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133\u0135\3\2"+
		"\2\2\u0134\u0132\3\2\2\2\u0135\u0136\7,\2\2\u0136\u0137\7\61\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0139\b\62\2\2\u0139d\3\2\2\2\u013a\u013c\7\17\2"+
		"\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e"+
		"\7\f\2\2\u013ef\3\2\2\2\u013f\u0143\4\62;\2\u0140\u0142\4\62;\2\u0141"+
		"\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2"+
		"\2\2\u0144h\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0148\4\62;\2\u0147\u0146"+
		"\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014f\7\60\2\2\u014c\u014e\4\62;\2\u014d\u014c\3"+
		"\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150"+
		"j\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u0153\13\2\2\2\u0153\u0154\3\2\2\2"+
		"\u0154\u0155\b\66\2\2\u0155l\3\2\2\2\r\2\u00d9\u00e5\u011c\u0122\u0127"+
		"\u0132\u013b\u0143\u0149\u014f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}