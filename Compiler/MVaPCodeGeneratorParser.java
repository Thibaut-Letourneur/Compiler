// Generated from MVaPCodeGenerator.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MVaPCodeGeneratorParser extends Parser {
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
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_condition = 2, RULE_declaration = 3, 
		RULE_assignation = 4, RULE_whileStruct = 5, RULE_ifStruct = 6, RULE_forStruct = 7, 
		RULE_repeatUntilStruct = 8, RULE_structure = 9, RULE_instruction = 10, 
		RULE_bloc = 11, RULE_instructionOrBloc = 12, RULE_fonction = 13, RULE_params = 14, 
		RULE_args = 15, RULE_expression = 16, RULE_finInstruction = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "calcul", "condition", "declaration", "assignation", "whileStruct", 
			"ifStruct", "forStruct", "repeatUntilStruct", "structure", "instruction", 
			"bloc", "instructionOrBloc", "fonction", "params", "args", "expression", 
			"finInstruction"
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

	@Override
	public String getGrammarFileName() { return "MVaPCodeGenerator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public MVaPCodeGeneratorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public CalculContext calcul;
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MVaPCodeGeneratorParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			((StartContext)_localctx).calcul = calcul();
			setState(37);
			match(EOF);
			 System.out.println(((StartContext)_localctx).calcul.code); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclarationContext declaration;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterCalcul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitCalcul(this);
		}
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_calcul);
		 ((CalculContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(40);
				((CalculContext)_localctx).declaration = declaration();
				 _localctx.code += ((CalculContext)_localctx).declaration.code; 
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					match(NEWLINE);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			 _localctx.code += "JUMP Main\n"; 
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(55);
				((CalculContext)_localctx).fonction = fonction();
				 _localctx.code += ((CalculContext)_localctx).fonction.code; 
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(63);
					match(NEWLINE);
					}
					} 
				}
				setState(68);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			 _localctx.code += "LABEL Main\n"; 
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__31) | (1L << WHILE) | (1L << FOR) | (1L << REPEAT) | (1L << IF) | (1L << RETURN) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIANT) | (1L << ENTIER) | (1L << REEL))) != 0)) {
				{
				{
				setState(70);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(78);
				match(NEWLINE);
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 _localctx.code += "HALT\n"; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public ConditionContext conditionL;
		public ConditionContext condition;
		public ExpressionContext expressionL;
		public ExpressionContext expression;
		public Token op;
		public ExpressionContext expressionR;
		public ConditionContext conditionR;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode TRUE() { return getToken(MVaPCodeGeneratorParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MVaPCodeGeneratorParser.FALSE, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_condition, _p);
		 ((ConditionContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(87);
				match(T__0);
				setState(88);
				((ConditionContext)_localctx).condition = condition(0);
				setState(89);
				match(T__1);

				        ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).condition.code;
				    
				}
				break;
			case 2:
				{
				setState(92);
				match(T__2);
				setState(93);
				((ConditionContext)_localctx).condition = condition(8);

				      ((ConditionContext)_localctx).code =   ((ConditionContext)_localctx).condition.code;

				      _localctx.code += "PUSHI 1\n";
				      _localctx.code += "NEQ\n";
				    
				}
				break;
			case 3:
				{
				setState(96);
				((ConditionContext)_localctx).expressionL = ((ConditionContext)_localctx).expression = expression(0);
				setState(97);
				((ConditionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
					((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(98);
				((ConditionContext)_localctx).expressionR = ((ConditionContext)_localctx).expression = expression(0);

				        boolean isDouble = ((ConditionContext)_localctx).expressionL.type.contains("double") || ((ConditionContext)_localctx).expressionR.type.contains("double");
				        String opCode = (isDouble ? "F" : "") + ((((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null).equals("==") ? "EQUAL" : "NEQ");

				        _localctx.code += expressionImplicitCast(((ConditionContext)_localctx).expressionL.code, ((ConditionContext)_localctx).expressionL.type, ((ConditionContext)_localctx).expressionR.code, ((ConditionContext)_localctx).expressionR.type);
				        if(_localctx.code.equals(""))//Il n'y a pas eu d'implicit cast
				            ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).expressionL.code + ((ConditionContext)_localctx).expressionR.code;//On rajoute les deux expressions sans cast

				        _localctx.code += opCode + "\n";
				    
				}
				break;
			case 4:
				{
				setState(101);
				((ConditionContext)_localctx).expressionL = ((ConditionContext)_localctx).expression = expression(0);
				setState(102);
				((ConditionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) ) {
					((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(103);
				((ConditionContext)_localctx).expressionR = ((ConditionContext)_localctx).expression = expression(0);

				        boolean isDouble = ((ConditionContext)_localctx).expressionL.type.contains("double") || ((ConditionContext)_localctx).expressionR.type.contains("double");
				        String opCode = (isDouble ? "F" : "") + ((((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null).equals("<") ? "INF" : (((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null).equals("<=") ? "INFEQ" : (((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null).equals(">") ? "SUP" : "SUPEQ");

				        _localctx.code += expressionImplicitCast(((ConditionContext)_localctx).expressionL.code, ((ConditionContext)_localctx).expressionL.type, ((ConditionContext)_localctx).expressionR.code, ((ConditionContext)_localctx).expressionR.type);
				        if(_localctx.code.equals(""))//Il n'y a pas eu d'implicit cast
				            ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).expressionL.code + ((ConditionContext)_localctx).expressionR.code;//On rajoute les deux expressions sans cast

				        _localctx.code += opCode + "\n";

				    
				}
				break;
			case 5:
				{
				setState(106);
				((ConditionContext)_localctx).expression = expression(0);

				        ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).expression.code;
				        _localctx.code += "PUSHI 0\n";
				        _localctx.code += "NEQ\n";
				    
				}
				break;
			case 6:
				{
				setState(109);
				match(TRUE);
				((ConditionContext)_localctx).code =  "PUSHI 1\n";
				}
				break;
			case 7:
				{
				setState(111);
				match(FALSE);
				((ConditionContext)_localctx).code =  "PUSHI 0\n";
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(125);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.conditionL = _prevctx;
						_localctx.conditionL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(115);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(116);
						match(T__10);
						setState(117);
						((ConditionContext)_localctx).conditionR = ((ConditionContext)_localctx).condition = condition(6);

						                  ((ConditionContext)_localctx).code =   ((ConditionContext)_localctx).conditionL.code + ((ConditionContext)_localctx).conditionR.code;
						                  _localctx.code += "ADD\n";
						                  _localctx.code += "PUSHI 2\n";
						                  _localctx.code += "EQUAL\n";
						              
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.conditionL = _prevctx;
						_localctx.conditionL = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(120);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(121);
						match(T__11);
						setState(122);
						((ConditionContext)_localctx).conditionR = ((ConditionContext)_localctx).condition = condition(5);

						                  ((ConditionContext)_localctx).code =   ((ConditionContext)_localctx).conditionL.code + ((ConditionContext)_localctx).conditionR.code;
						                  _localctx.code += "ADD\n";
						                  _localctx.code += "PUSHI 1\n";
						                  _localctx.code += "SUPEQ\n";
						              
						}
						break;
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public Token TYPE;
		public ExpressionContext expression;
		public Token ENTIER;
		public TerminalNode IDENTIFIANT() { return getToken(MVaPCodeGeneratorParser.IDENTIFIANT, 0); }
		public TerminalNode TYPE() { return getToken(MVaPCodeGeneratorParser.TYPE, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ENTIER() { return getToken(MVaPCodeGeneratorParser.ENTIER, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		 ((DeclarationContext)_localctx).code =  new String(); 
		try {
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(T__12);
				setState(131);
				((DeclarationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(132);
				match(T__13);
				setState(133);
				((DeclarationContext)_localctx).TYPE = match(TYPE);
				setState(134);
				finInstruction();

				            String type = (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null);

				            if(type.equals("int"))
				                ((DeclarationContext)_localctx).code =  "PUSHI 0\n";//Reservation d'un espace sur la pile
				            else if(type.equals("double"))
				                ((DeclarationContext)_localctx).code =  "PUSHF 0.0\n";
				            else if(type.equals("bool") || type.equals("boolean"))
				            {
				                type = "bool";
				                ((DeclarationContext)_localctx).code =  "PUSHI 0\n";//Reservation d'un espace sur la pile
				            }
				            else
				                throw new Error("ERROR: Unrecognized type for variable declaration : var " + (((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null) + " : " + (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null));

				            tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null), type);
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(T__12);
				setState(138);
				((DeclarationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(139);
				match(T__13);
				setState(140);
				((DeclarationContext)_localctx).TYPE = match(TYPE);
				setState(141);
				match(T__14);
				setState(142);
				((DeclarationContext)_localctx).expression = expression(0);
				setState(143);
				finInstruction();

				            boolean isDouble = (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null).equals("double");
				            boolean castDouble = false;

				            ((DeclarationContext)_localctx).code =  ((DeclarationContext)_localctx).expression.code;

				            if(isDouble && ((DeclarationContext)_localctx).expression.type.contains("int"))
				            {
				                System.err.println("WARNING: Assigning an integer to a double variable for variable " + (((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null));
				                castDouble = true;
				            }
				            else if(((((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null).equals("boolean") || (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null).contains("bool")) && !(((DeclarationContext)_localctx).expression.type.contains("bool")))
				            //La variable est un booléen mais l'expression sur la droite n'est pas un booléen
				                throw new Error("ERROR: You cannot assign a non-boolean expression to a boolean variable.");
				            else if(!isDouble && ((DeclarationContext)_localctx).expression.type.contains("double"))
				                throw new Error("ERROR: Assigning a double to the non-double variable " + (((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null));

				            if(castDouble)
				                _localctx.code += "ITOF\n";

				            tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null), (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null));
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				match(T__12);
				setState(147);
				((DeclarationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(148);
				match(T__13);
				setState(149);
				((DeclarationContext)_localctx).TYPE = match(TYPE);
				setState(150);
				match(T__15);
				setState(151);
				((DeclarationContext)_localctx).ENTIER = match(ENTIER);
				setState(152);
				match(T__16);
				setState(153);
				finInstruction();

				            int size = Integer.parseInt((((DeclarationContext)_localctx).ENTIER!=null?((DeclarationContext)_localctx).ENTIER.getText():null));
				            boolean isDouble = (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null).equals("double");
				            String type = (((DeclarationContext)_localctx).TYPE!=null?((DeclarationContext)_localctx).TYPE.getText():null);

				            if(type.equals("double") && !doubleRegisterAllocated)//Si c'est un tableau de double et
				            //que le registre réservé n'a pas encore été alloué
				            {
				                tablesSymboles.putVar(RESERVED_DOUBLE_REGISTER_LABEL, "int");
				                doubleRegisterAllocated = true;//Le registre a été alloué

				                _localctx.code += "PUSHI 0\n";//On push un octet pour allouer l'espace du registre sur la pile
				            }
				            else if(type.equals("boolean"))
				                type = "bool";

				            _localctx.code += "ALLOC " + (size * (isDouble ? 2 : 1)) + "\n";//On multiplie par deux la taille de l'allocation pour les doubles
				            tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null), "array" + type);
				            for(int i = 1; i < size; i++)//On va réserver un emplacement dans la table des symboles pour chaque
				            //indice du tableau que l'on vient de déclarer
				                tablesSymboles.putVar((((DeclarationContext)_localctx).IDENTIFIANT!=null?((DeclarationContext)_localctx).IDENTIFIANT.getText():null) + "[" + i + "]", type);
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext index;
		public ExpressionContext expression;
		public Token op;
		public TerminalNode IDENTIFIANT() { return getToken(MVaPCodeGeneratorParser.IDENTIFIANT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitAssignation(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignation);
		 ((AssignationContext)_localctx).code =  new String(); 
		int _la;
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(159);
					match(T__15);
					setState(160);
					((AssignationContext)_localctx).index = expression(0);
					setState(161);
					match(T__16);
					}
				}

				setState(165);
				match(T__17);

				            AdresseType adresseType = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				            boolean isDouble = adresseType.type.contains("double");
				            boolean isArray = adresseType.type.contains("array");
				            printErrorIfBool(adresseType.type, "++");//Emits an error if IDENTIFIANT is of type
				            //boolean as we cannot '++' a boolean

				            if(isArray && (((AssignationContext)_localctx).index!=null?_input.getText(((AssignationContext)_localctx).index.start,((AssignationContext)_localctx).index.stop):null) == null)//La variable d'un tableau a été donnée mais aucun index n'a été donné
				                throw new Error("ERROR: Operator '++' cannot be applied to an array.");
				            else if(isArray)//Un index a été donné
				            {
				                checkArrayIndexType(((AssignationContext)_localctx).index.type);
				                if(adresseType.adresse < 0)//On utilise ici un pointeur sur le début du tableau
				                    _localctx.code += pushArrayIndexesPointer(adresseType.type, ((AssignationContext)_localctx).index.code, adresseType.adresse);
				                else
				                    _localctx.code += pushArrayIndexes(adresseType.type, ((AssignationContext)_localctx).index.code);
				            }

				            //On push 1 pour ajouter 1 avec '++'
				            _localctx.code += "PUSH" + (isDouble ? "F" : "I") + " 1" + (isDouble ? ".0" : "") + "\n";
				            _localctx.code += pushVar(adresseType.adresse, adresseType.type, (isArray ? ((AssignationContext)_localctx).index.code : null));
				            _localctx.code += (isDouble ? "F" : "") + "ADD" + "\n";
				            _localctx.code += storeVar(adresseType.adresse, adresseType.type);
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(168);
					match(T__15);
					setState(169);
					((AssignationContext)_localctx).index = expression(0);
					setState(170);
					match(T__16);
					}
				}

				setState(174);
				match(T__18);

				            AdresseType adresseType = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				            boolean isDouble = adresseType.type.contains("double");
				            boolean isArray = adresseType.type.contains("array");
				            printErrorIfBool(adresseType.type, "--");//Emits an error if IDENTIFIANT is of type
				            //boolean as we cannot '--' a boolean

				            if(isArray && (((AssignationContext)_localctx).index!=null?_input.getText(((AssignationContext)_localctx).index.start,((AssignationContext)_localctx).index.stop):null) == null)//La variable d'un tableau a été donnée mais aucun index n'a été donné
				                throw new Error("ERROR: Operator '--' cannot be applied to an array.");
				            else if(isArray)//Un index a été spécifié
				            {
				                checkArrayIndexType(((AssignationContext)_localctx).index.type);
				                if(adresseType.adresse < 0)//On utilise ici un pointeur sur le début du tableau
				                    _localctx.code += pushArrayIndexesPointer(adresseType.type, ((AssignationContext)_localctx).index.code, adresseType.adresse);
				                else
				                    _localctx.code += pushArrayIndexes(adresseType.type, ((AssignationContext)_localctx).index.code);
				            }

				            _localctx.code += pushVar(adresseType.adresse, adresseType.type, (isArray ? ((AssignationContext)_localctx).index.code : null));
				            _localctx.code += "PUSH" + (isDouble ? "F" : "I") + " 1" + (isDouble ? ".0" : "") + "\n";
				            _localctx.code += (isDouble ? "F" : "") + "SUB" + "\n";
				            _localctx.code += storeVar(adresseType.adresse, adresseType.type);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(176);
				((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(177);
					match(T__15);
					setState(178);
					((AssignationContext)_localctx).index = ((AssignationContext)_localctx).expression = expression(0);
					setState(179);
					match(T__16);
					}
				}

				setState(183);
				((AssignationContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22))) != 0)) ) {
					((AssignationContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(184);
				((AssignationContext)_localctx).expression = expression(0);

				            AdresseType adresseType = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
				            Integer adresse = adresseType.adresse;
				            String type = adresseType.type;
				            boolean isArray = adresseType.type.contains("array");

				            if(!(((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("="))
				            {
				                //Emits an error if IDENTIFIANT is of type
				                //boolean as we cannot use '+=', '-=', '*=' or '/=' on a boolean
				                printErrorIfBool(type, (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null));
				                printErrorIfBool(((AssignationContext)_localctx).expression.type, (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null));//Same for expression
				            }
				            //Checking that we're using the '=' between two booleans
				            else if(((AssignationContext)_localctx).expression.type.contains("bool") && !type.contains("bool"))
				                throw new Error("ERROR: You cannot assign a boolean to a variable of type '" + type + "'");
				            else if(!((AssignationContext)_localctx).expression.type.contains("bool") && type.contains("bool"))
				                throw new Error("ERROR: You cannot assign a non-boolean value to a boolean variable");

				            if(isArray && (((AssignationContext)_localctx).index!=null?_input.getText(((AssignationContext)_localctx).index.start,((AssignationContext)_localctx).index.stop):null) == null)//La variable d'un tableau a été donnée mais aucun index n'a été donné
				                throw new Error("ERROR: Operator '" + (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null) + "' cannot be applied to an array.");
				            else if(isArray)//Un index a été spécifié, on veut donc assigner à une case d'un tableau
				            {
				                checkArrayIndexType(((AssignationContext)_localctx).index.type);
				                if(adresse < 0)//La variable est un tableau mais nous avons une adresse négative, nous avons
				                //donc accès ici à un pointeur sur le début du tableau et non pas le tableau directement
				                    _localctx.code += pushArrayIndexesPointer(type, ((AssignationContext)_localctx).index.code, adresse);
				                else
				                    _localctx.code += pushArrayIndexes(type, ((AssignationContext)_localctx).index.code);//On va push les index pour préparer les futures
				                //instructions STORER
				            }

				            if((((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("-=") || (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("/="))//Si on a '-=' ou '/=', on va d'abord devoir
				            //push la variable et ensuite 'expression'
				                _localctx.code += pushVar(adresse, type, (isArray ? ((AssignationContext)_localctx).index.code : null));
				            else
				            {
				                _localctx.code += ((AssignationContext)_localctx).expression.code;
				                if(type.contains("double") && ((AssignationContext)_localctx).expression.type.contains("int"))//Si la variable à laquelle on est en
				                //train d'assigner une nouvelle valeur est un double et que la valeur qu'on essaie d'assigner
				                //est un int, on va cast le int en float
				                {
				                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null), ((AssignationContext)_localctx).expression.type));
				                    _localctx.code += "ITOF\n";
				                }
				                else if(type.contains("int") && ((AssignationContext)_localctx).expression.type.contains("double"))//Vice-versa
				                {
				                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null), ((AssignationContext)_localctx).expression.type));
				                    _localctx.code += "FTOI\n";
				                }
				            }

				            if((((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("="))
				                _localctx.code += storeVar(adresse, type);
				            else if((((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("+="))
				            {
				                _localctx.code += pushVar(adresse, type, (isArray ? ((AssignationContext)_localctx).index.code : null));

				                _localctx.code += (type.contains("double") ? "F" : "") + "ADD\n";
				                _localctx.code += storeVar(adresse, type);
				            }
				            else if((((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("-="))
				            {
				                _localctx.code += ((AssignationContext)_localctx).expression.code;
				                if(type.contains("double") && ((AssignationContext)_localctx).expression.type.contains("int"))//Si la variable à laquelle on est en
				                //train d'assigner une nouvelle valeur est un float et que la valeur qu'on essaie d'assigner
				                //est un int, on va cast le int en float
				                {
				                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null), ((AssignationContext)_localctx).expression.type));
				                    _localctx.code += "ITOF\n";
				                }

				                _localctx.code += (type.contains("double") ? "F" : "") + "SUB\n";
				                _localctx.code += storeVar(adresse, type);
				            }
				            else if((((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("*="))
				            {
				                _localctx.code += pushVar(adresse, type, (isArray ? ((AssignationContext)_localctx).index.code : null));

				                _localctx.code += (type.contains("double") ? "F" : "") + "MUL\n";
				                _localctx.code += storeVar(adresse, type);
				            }
				            else if((((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null).equals("/="))
				            {
				                _localctx.code += ((AssignationContext)_localctx).expression.code;
				                if(type.contains("double") && ((AssignationContext)_localctx).expression.type.contains("int"))//Si la variable à laquelle on est en
				                //train d'assigner une nouvelle valeur est un float et que la valeur qu'on essaie d'assigner
				                //est un int, on va cast le int en float
				                {
				                    System.err.println("WARNING: Operating on two different types ! --> " + String.format("%s %s %s", type, (((AssignationContext)_localctx).op!=null?((AssignationContext)_localctx).op.getText():null), ((AssignationContext)_localctx).expression.type));
				                    _localctx.code += "ITOF\n";
				                }

				                _localctx.code += (type.contains("double") ? "F" : "") + "DIV\n";
				                _localctx.code += storeVar(adresse, type);
				            }
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStructContext extends ParserRuleContext {
		public String code;
		public ConditionContext condition;
		public InstructionOrBlocContext instructionOrBloc;
		public TerminalNode WHILE() { return getToken(MVaPCodeGeneratorParser.WHILE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionOrBlocContext instructionOrBloc() {
			return getRuleContext(InstructionOrBlocContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public WhileStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterWhileStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitWhileStruct(this);
		}
	}

	public final WhileStructContext whileStruct() throws RecognitionException {
		WhileStructContext _localctx = new WhileStructContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whileStruct);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(WHILE);
			setState(190);
			match(T__0);
			setState(191);
			((WhileStructContext)_localctx).condition = condition(0);
			setState(192);
			match(T__1);
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(193);
				match(NEWLINE);
				}
				}
				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(199);
			((WhileStructContext)_localctx).instructionOrBloc = instructionOrBloc();
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(200);
					match(NEWLINE);
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}

			      String labelStart = getNewLabel();
			      String labelEnd   = getNewLabel();

			      ((WhileStructContext)_localctx).code =  "LABEL " + labelStart + "\n";
			      _localctx.code += ((WhileStructContext)_localctx).condition.code;
			      _localctx.code += "JUMPF " + labelEnd + "\n";
			      _localctx.code += ((WhileStructContext)_localctx).instructionOrBloc.code;
			      _localctx.code += "JUMP " + labelStart + "\n";
			      _localctx.code += "LABEL " + labelEnd + "\n";
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStructContext extends ParserRuleContext {
		public String code;
		public ConditionContext condition;
		public InstructionOrBlocContext ifIns;
		public InstructionOrBlocContext elseIns;
		public InstructionOrBlocContext instructionOrBloc;
		public TerminalNode IF() { return getToken(MVaPCodeGeneratorParser.IF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(MVaPCodeGeneratorParser.ELSE, 0); }
		public List<InstructionOrBlocContext> instructionOrBloc() {
			return getRuleContexts(InstructionOrBlocContext.class);
		}
		public InstructionOrBlocContext instructionOrBloc(int i) {
			return getRuleContext(InstructionOrBlocContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public IfStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterIfStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitIfStruct(this);
		}
	}

	public final IfStructContext ifStruct() throws RecognitionException {
		IfStructContext _localctx = new IfStructContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifStruct);
		int _la;
		try {
			int _alt;
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(IF);
				setState(209);
				match(T__0);
				setState(210);
				((IfStructContext)_localctx).condition = condition(0);
				setState(211);
				match(T__1);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(212);
					match(NEWLINE);
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(218);
				((IfStructContext)_localctx).ifIns = instructionOrBloc();
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(219);
					match(NEWLINE);
					}
					}
					setState(224);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(225);
				match(ELSE);
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(226);
					match(NEWLINE);
					}
					}
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(232);
				((IfStructContext)_localctx).elseIns = instructionOrBloc();
				setState(236);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(233);
						match(NEWLINE);
						}
						} 
					}
					setState(238);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}

				    String elseLabel = getNewLabel();
				    String endLabel = getNewLabel();

				    ((IfStructContext)_localctx).code =  ((IfStructContext)_localctx).condition.code;
				    _localctx.code += "JUMPF " + elseLabel + "\n";
				    _localctx.code += ((IfStructContext)_localctx).ifIns.code;
				    _localctx.code += "JUMP " + endLabel + "\n";//Pour ne pas exécuter le code de la branche 'else''
				    _localctx.code += "LABEL " + elseLabel + "\n";
				    _localctx.code += ((IfStructContext)_localctx).elseIns.code;
				    _localctx.code += "LABEL " + endLabel + "\n";
				  
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(IF);
				setState(242);
				match(T__0);
				setState(243);
				((IfStructContext)_localctx).condition = condition(0);
				setState(244);
				match(T__1);
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(245);
					match(NEWLINE);
					}
					}
					setState(250);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(251);
				((IfStructContext)_localctx).instructionOrBloc = instructionOrBloc();
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(252);
						match(NEWLINE);
						}
						} 
					}
					setState(257);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}

				      String labelEnd = getNewLabel();

				      ((IfStructContext)_localctx).code =  ((IfStructContext)_localctx).condition.code;
				      _localctx.code += "JUMPF " + labelEnd + "\n";
				      _localctx.code += ((IfStructContext)_localctx).instructionOrBloc.code;
				      _localctx.code += "LABEL " + labelEnd + "\n";
				  
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStructContext extends ParserRuleContext {
		public String code;
		public AssignationContext startAssign;
		public ConditionContext condition;
		public AssignationContext endAssign;
		public InstructionOrBlocContext instructionOrBloc;
		public TerminalNode FOR() { return getToken(MVaPCodeGeneratorParser.FOR, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionOrBlocContext instructionOrBloc() {
			return getRuleContext(InstructionOrBlocContext.class,0);
		}
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public ForStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterForStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitForStruct(this);
		}
	}

	public final ForStructContext forStruct() throws RecognitionException {
		ForStructContext _localctx = new ForStructContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_forStruct);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(FOR);
			setState(263);
			match(T__0);
			setState(264);
			((ForStructContext)_localctx).startAssign = assignation();
			setState(265);
			match(T__23);
			setState(266);
			((ForStructContext)_localctx).condition = condition(0);
			setState(267);
			match(T__23);
			setState(268);
			((ForStructContext)_localctx).endAssign = assignation();
			setState(269);
			match(T__1);
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(270);
				match(NEWLINE);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(276);
			((ForStructContext)_localctx).instructionOrBloc = instructionOrBloc();
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(277);
					match(NEWLINE);
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}

			          String labelStart = getNewLabel();
			          String labelEnd = getNewLabel();

			          ((ForStructContext)_localctx).code =  ((ForStructContext)_localctx).startAssign.code;
			          _localctx.code += "LABEL " + labelStart + "\n";
			          _localctx.code += ((ForStructContext)_localctx).condition.code;
			          _localctx.code += "JUMPF " + labelEnd + "\n";
			          _localctx.code += ((ForStructContext)_localctx).instructionOrBloc.code;
			          _localctx.code += ((ForStructContext)_localctx).endAssign.code;
			          _localctx.code += "JUMP " + labelStart + "\n";
			          _localctx.code += "LABEL " + labelEnd + "\n";
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatUntilStructContext extends ParserRuleContext {
		public String code;
		public InstructionOrBlocContext instructionOrBloc;
		public ConditionContext condition;
		public TerminalNode REPEAT() { return getToken(MVaPCodeGeneratorParser.REPEAT, 0); }
		public InstructionOrBlocContext instructionOrBloc() {
			return getRuleContext(InstructionOrBlocContext.class,0);
		}
		public TerminalNode UNTIL() { return getToken(MVaPCodeGeneratorParser.UNTIL, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public RepeatUntilStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatUntilStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterRepeatUntilStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitRepeatUntilStruct(this);
		}
	}

	public final RepeatUntilStructContext repeatUntilStruct() throws RecognitionException {
		RepeatUntilStructContext _localctx = new RepeatUntilStructContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_repeatUntilStruct);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(REPEAT);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(286);
				match(NEWLINE);
				}
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(292);
			((RepeatUntilStructContext)_localctx).instructionOrBloc = instructionOrBloc();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(293);
				match(NEWLINE);
				}
				}
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(299);
			match(UNTIL);
			setState(300);
			match(T__0);
			setState(301);
			((RepeatUntilStructContext)_localctx).condition = condition(0);
			setState(302);
			match(T__1);
			setState(303);
			finInstruction();
			setState(307);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(304);
					match(NEWLINE);
					}
					} 
				}
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}

			            String labelStart = getNewLabel();

			            ((RepeatUntilStructContext)_localctx).code =  "LABEL " + labelStart + "\n";
			            _localctx.code += ((RepeatUntilStructContext)_localctx).instructionOrBloc.code;
			            _localctx.code += ((RepeatUntilStructContext)_localctx).condition.code;
			            _localctx.code += "JUMPF " + labelStart + "\n";
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructureContext extends ParserRuleContext {
		public String code;
		public WhileStructContext whileStruct;
		public IfStructContext ifStruct;
		public ForStructContext forStruct;
		public RepeatUntilStructContext repeatUntilStruct;
		public WhileStructContext whileStruct() {
			return getRuleContext(WhileStructContext.class,0);
		}
		public IfStructContext ifStruct() {
			return getRuleContext(IfStructContext.class,0);
		}
		public ForStructContext forStruct() {
			return getRuleContext(ForStructContext.class,0);
		}
		public RepeatUntilStructContext repeatUntilStruct() {
			return getRuleContext(RepeatUntilStructContext.class,0);
		}
		public StructureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitStructure(this);
		}
	}

	public final StructureContext structure() throws RecognitionException {
		StructureContext _localctx = new StructureContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_structure);
		try {
			setState(324);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				((StructureContext)_localctx).whileStruct = whileStruct();
				 ((StructureContext)_localctx).code =  ((StructureContext)_localctx).whileStruct.code;  
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				((StructureContext)_localctx).ifStruct = ifStruct();
				 ((StructureContext)_localctx).code =  ((StructureContext)_localctx).ifStruct.code;     
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(318);
				((StructureContext)_localctx).forStruct = forStruct();
				 ((StructureContext)_localctx).code =  ((StructureContext)_localctx).forStruct.code;    
				}
				break;
			case REPEAT:
				enterOuterAlt(_localctx, 4);
				{
				setState(321);
				((StructureContext)_localctx).repeatUntilStruct = repeatUntilStruct();
				 ((StructureContext)_localctx).code =  ((StructureContext)_localctx).repeatUntilStruct.code; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public StructureContext structure;
		public ExpressionContext expression;
		public AssignationContext assignation;
		public Token IDENTIFIANT;
		public StructureContext structure() {
			return getRuleContext(StructureContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public TerminalNode IDENTIFIANT() { return getToken(MVaPCodeGeneratorParser.IDENTIFIANT, 0); }
		public TerminalNode RETURN() { return getToken(MVaPCodeGeneratorParser.RETURN, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_instruction);
		try {
			setState(358);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(326);
				((InstructionContext)_localctx).structure = structure();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).structure.code;  
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				((InstructionContext)_localctx).expression = expression(0);
				setState(330);
				finInstruction();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(333);
				((InstructionContext)_localctx).assignation = assignation();
				setState(334);
				finInstruction();
				 ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(337);
				match(T__23);
				 ((InstructionContext)_localctx).code =  ""; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(339);
				match(T__24);
				setState(340);
				match(T__0);
				setState(341);
				((InstructionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(342);
				match(T__1);
				setState(343);
				finInstruction();

				            AdresseType adrType = tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null));

				            if(adrType.type.contains("double"))
				            {
				                ((InstructionContext)_localctx).code =   "READF\n";
				                _localctx.code += storeVar(adrType.adresse, "double");
				            }
				            else
				                ((InstructionContext)_localctx).code =  "READ\n" + "STOREG " + tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null)).adresse + "\n";
				        
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(346);
				match(T__25);
				setState(347);
				match(T__0);
				setState(348);
				((InstructionContext)_localctx).expression = expression(0);
				setState(349);
				match(T__1);
				setState(350);
				finInstruction();

				            boolean isDouble = ((InstructionContext)_localctx).expression.type.contains("double");

				            ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code + "WRITE" + (isDouble ? "F" : "") + "\n";
				            if(isDouble)
				                _localctx.code += "POP\nPOP\n";
				            else
				                _localctx.code += "POP\n";
				        
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(353);
				match(RETURN);
				setState(354);
				((InstructionContext)_localctx).expression = expression(0);
				setState(355);
				finInstruction();

				            ((InstructionContext)_localctx).code =  "";

				            if(tablesSymboles.getAdresseType("returnVar") != null)//Si la fonction n'est pas de type void et que "returnVar"
				            //a été déclarée
				                _localctx.code += ((InstructionContext)_localctx).expression.code + storeVar(tablesSymboles.getAdresseType("returnVar").adresse, ((InstructionContext)_localctx).expression.type);

				            _localctx.code += "RETURN\n";
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitBloc(this);
		}
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bloc);
		 ((BlocContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(T__26);
			setState(364);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(361);
					match(NEWLINE);
					}
					} 
				}
				setState(366);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__31) | (1L << WHILE) | (1L << FOR) | (1L << REPEAT) | (1L << IF) | (1L << RETURN) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIANT) | (1L << ENTIER) | (1L << REEL))) != 0)) {
				{
				{
				setState(367);
				((BlocContext)_localctx).instruction = instruction();
				 _localctx.code += ((BlocContext)_localctx).instruction.code; 
				}
				}
				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(375);
				match(NEWLINE);
				}
				}
				setState(380);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(381);
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionOrBlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public BlocContext bloc;
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public InstructionOrBlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionOrBloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterInstructionOrBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitInstructionOrBloc(this);
		}
	}

	public final InstructionOrBlocContext instructionOrBloc() throws RecognitionException {
		InstructionOrBlocContext _localctx = new InstructionOrBlocContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_instructionOrBloc);
		try {
			setState(389);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__23:
			case T__24:
			case T__25:
			case T__31:
			case WHILE:
			case FOR:
			case REPEAT:
			case IF:
			case RETURN:
			case TRUE:
			case FALSE:
			case IDENTIFIANT:
			case ENTIER:
			case REEL:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				((InstructionOrBlocContext)_localctx).instruction = instruction();
				 ((InstructionOrBlocContext)_localctx).code =  ((InstructionOrBlocContext)_localctx).instruction.code; 
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(386);
				((InstructionOrBlocContext)_localctx).bloc = bloc();
				 ((InstructionOrBlocContext)_localctx).code =  ((InstructionOrBlocContext)_localctx).bloc.code; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public Token type;
		public BlocContext bloc;
		public TerminalNode IDENTIFIANT() { return getToken(MVaPCodeGeneratorParser.IDENTIFIANT, 0); }
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(MVaPCodeGeneratorParser.TYPE, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fonction);
		 tablesSymboles.newTableLocale(); ((FonctionContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(T__28);
			setState(392);
			((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(393);
			match(T__13);
			setState(394);
			((FonctionContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==TYPE) ) {
				((FonctionContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

			            tablesSymboles.newFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null), (((FonctionContext)_localctx).type!=null?((FonctionContext)_localctx).type.getText():null));//On ajoute la fonction à la table des symboles
			            if(!(((FonctionContext)_localctx).type!=null?((FonctionContext)_localctx).type.getText():null).equals("void"))
			                tablesSymboles.putVar("returnVar", (((FonctionContext)_localctx).type!=null?((FonctionContext)_localctx).type.getText():null));

			            _localctx.code += "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
			    	
			setState(396);
			match(T__0);
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(397);
				params();
				}
			}

			setState(400);
			match(T__1);
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(401);
				match(NEWLINE);
				}
				}
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(407);
			((FonctionContext)_localctx).bloc = bloc();
			setState(411);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(408);
					match(NEWLINE);
					}
					} 
				}
				setState(413);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}

			            _localctx.code += ((FonctionContext)_localctx).bloc.code;
			            _localctx.code += "RETURN\n";//Safety return
			        
			}
			_ctx.stop = _input.LT(-1);
			 tablesSymboles.dropTableLocale(); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIANT;
		public List<TerminalNode> TYPE() { return getTokens(MVaPCodeGeneratorParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(MVaPCodeGeneratorParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIANT() { return getTokens(MVaPCodeGeneratorParser.IDENTIFIANT); }
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(MVaPCodeGeneratorParser.IDENTIFIANT, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(416);
				((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(417);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				        tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
				    
				}
				break;
			case 2:
				{
				setState(419);
				((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(420);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(421);
				match(T__15);
				setState(422);
				match(T__16);

				        tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), "array" + (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
				    
				}
				break;
			}
			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				setState(436);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					{
					setState(426);
					match(T__30);
					setState(427);
					((ParamsContext)_localctx).TYPE = match(TYPE);
					setState(428);
					((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

					            tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
					        
					}
					}
					break;
				case 2:
					{
					{
					setState(430);
					match(T__30);
					setState(431);
					((ParamsContext)_localctx).TYPE = match(TYPE);
					setState(432);
					((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
					setState(433);
					match(T__15);
					setState(434);
					match(T__16);

					            tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), "array" + (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));//On réserve un octet pour l'adresse du tableau
					        
					}
					}
					break;
				}
				}
				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__31) | (1L << TRUE) | (1L << FALSE) | (1L << IDENTIFIANT) | (1L << ENTIER) | (1L << REEL))) != 0)) {
				{
				setState(441);
				((ArgsContext)_localctx).expression = expression(0);

				            _localctx.code += ((ArgsContext)_localctx).expression.code;

				            //Si on passe un tableau à la fonction, on ne va passer que le pointeur donc c'est de taille 1
				            //Sinon, si c'est un double, taille 2, sinon taille 1
				            _localctx.size += (((ArgsContext)_localctx).expression.type.contains("array") ? 1 : ((ArgsContext)_localctx).expression.type.equals("double") ? 2 : 1);
				        
				setState(449);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(443);
					match(T__30);
					setState(444);
					((ArgsContext)_localctx).expression = expression(0);

					            _localctx.code += ((ArgsContext)_localctx).expression.code;
					            _localctx.size += (((ArgsContext)_localctx).expression.type.contains("array") ? 1 : ((ArgsContext)_localctx).expression.type.equals("double") ? 2 : 1);
					        
					}
					}
					setState(451);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ExpressionContext a;
		public ExpressionContext expression;
		public Token TYPE;
		public Token nb;
		public Token IDENTIFIANT;
		public ExpressionContext index;
		public ArgsContext args;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode TYPE() { return getToken(MVaPCodeGeneratorParser.TYPE, 0); }
		public TerminalNode ENTIER() { return getToken(MVaPCodeGeneratorParser.ENTIER, 0); }
		public TerminalNode REEL() { return getToken(MVaPCodeGeneratorParser.REEL, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(MVaPCodeGeneratorParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode TRUE() { return getToken(MVaPCodeGeneratorParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MVaPCodeGeneratorParser.FALSE, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expression, _p);
		 ((ExpressionContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(455);
				match(T__0);
				setState(456);
				((ExpressionContext)_localctx).expression = expression(0);
				setState(457);
				match(T__1);
				((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).expression.code; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type;
				}
				break;
			case 2:
				{
				setState(460);
				match(T__31);
				setState(461);
				((ExpressionContext)_localctx).expression = expression(9);

				        //If expression is a boolean expression, throw an error
				        if(((ExpressionContext)_localctx).expression.type.contains("bool") || ((ExpressionContext)_localctx).expression.type.contains("bool"))
				            throw new Error("ERROR: The unary operator '-' is not supported for the boolean type");
				        boolean isDouble = ((ExpressionContext)_localctx).expression.type.contains("double");

				        _localctx.code += "PUSH" + (isDouble ? "F" : "I") + " 0" + (isDouble ? ".0" : "") + "\n";
				        _localctx.code += ((ExpressionContext)_localctx).expression.code;
				        _localctx.code += (isDouble ? "F" : "") + "SUB" + "\n";

				        ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type;
				    
				}
				break;
			case 3:
				{
				setState(464);
				match(T__0);
				setState(465);
				((ExpressionContext)_localctx).TYPE = match(TYPE);
				setState(466);
				match(T__1);
				setState(467);
				((ExpressionContext)_localctx).expression = expression(6);

				        ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).expression.code;

				        if(((ExpressionContext)_localctx).expression.type.contains("double") && (((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals("int"))//Cast explicite de double en int
				        {
				            _localctx.code += "FTOI\n";
				            ((ExpressionContext)_localctx).type =  "int";
				        }
				        else if(!((ExpressionContext)_localctx).expression.type.contains("double") && (((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals("double"))//Cast explicite de bool ou int en double
				        {
				            _localctx.code += "ITOF\n";
				            ((ExpressionContext)_localctx).type =  "double";
				        }
				        else if((((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals("bool") || (((ExpressionContext)_localctx).TYPE!=null?((ExpressionContext)_localctx).TYPE.getText():null).equals("boolean"))//Cast d'une valeur en bool
				        {
				            if(((ExpressionContext)_localctx).expression.type.contains("double"))//L'expression à caster est un double, on va regarder si c'est différent de 0.0 ou pas
				            {
				                _localctx.code += "PUSHF 0.0\n";
				                _localctx.code += "FNEQ\n";
				            }
				            else if(((ExpressionContext)_localctx).expression.type.contains("int"))//L'expression à caster en bool est un int, on va regarder si c'est différent de 0 ou pas
				            {
				                _localctx.code += "PUSHI 0\n";
				                _localctx.code += "NEQ\n";
				            }

				            ((ExpressionContext)_localctx).type =  "bool";
				        }
				        else//Cast du même type, rien à faire
				        {
				            ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).expression.code;

				            ((ExpressionContext)_localctx).type =  (((ExpressionContext)_localctx).expression!=null?_input.getText(((ExpressionContext)_localctx).expression.start,((ExpressionContext)_localctx).expression.stop):null);
				        }
				    
				}
				break;
			case 4:
				{
				setState(470);
				((ExpressionContext)_localctx).nb = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ENTIER || _la==REEL) ) {
					((ExpressionContext)_localctx).nb = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				        boolean isDouble = (((ExpressionContext)_localctx).nb!=null?((ExpressionContext)_localctx).nb.getText():null).contains(".");

				        ((ExpressionContext)_localctx).code =  "PUSH" + (isDouble ? "F" : "I") + " " + (((ExpressionContext)_localctx).nb!=null?((ExpressionContext)_localctx).nb.getText():null) + "\n";
				        ((ExpressionContext)_localctx).type =  (isDouble ? "double" : "int");
				    
				}
				break;
			case 5:
				{
				setState(472);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(477);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(473);
					match(T__15);
					setState(474);
					((ExpressionContext)_localctx).index = ((ExpressionContext)_localctx).expression = expression(0);
					setState(475);
					match(T__16);
					}
					break;
				}

				        AdresseType adrType = tablesSymboles.getAdresseType((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));

				        boolean isArray = adrType.type.contains("array");

				        if(isArray && (((ExpressionContext)_localctx).index!=null?_input.getText(((ExpressionContext)_localctx).index.start,((ExpressionContext)_localctx).index.stop):null) != null)
				            checkArrayIndexType(((ExpressionContext)_localctx).index.type);
				        if(!isArray & (((ExpressionContext)_localctx).index!=null?_input.getText(((ExpressionContext)_localctx).index.start,((ExpressionContext)_localctx).index.stop):null) != null)//On essayer d'indexer une variable qui n'est pas un tableau
				            throw new Error("ERROR: You cannot index the non-array variable '" + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "'");

				        if(isArray & (((ExpressionContext)_localctx).index!=null?_input.getText(((ExpressionContext)_localctx).index.start,((ExpressionContext)_localctx).index.stop):null) == null)//Seulement le non d'un tableau (sans indexing) a été précisé
				            ((ExpressionContext)_localctx).code =  "PUSHI " + adrType.adresse + "\n";//La variable est un tableau, on push l'adresse du tableau
				        else//On push la valeur de la variable
				            ((ExpressionContext)_localctx).code =  pushVar(adrType.adresse, adrType.type, (isArray ? ((ExpressionContext)_localctx).index.code : null));

				        ((ExpressionContext)_localctx).type =  adrType.type;
				    
				}
				break;
			case 6:
				{
				setState(480);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(481);
				match(T__0);
				setState(482);
				((ExpressionContext)_localctx).args = args();
				setState(483);
				match(T__1);

				        String typeFonction = tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				        if(typeFonction == null)//La fonction n'est pas définie
				            return null;

				        ((ExpressionContext)_localctx).code =  "";
				        if(!typeFonction.equals("void"))
				        {
				            boolean isFunctionDouble = typeFonction.equals("double");
				            ((ExpressionContext)_localctx).code =  "PUSH" + (isFunctionDouble ? "F" : "I") + " 0" + (isFunctionDouble ? ".0" : "") + "\n";//Valeur de retour
				        }
				        _localctx.code += ((ExpressionContext)_localctx).args.code;//On push les arguments sur la stack

				        _localctx.code += "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
				        for(int i = 0; i < ((ExpressionContext)_localctx).args.size; i++)//POP de tous les arguments précédemment passés
				            _localctx.code += "POP\n";

				        ((ExpressionContext)_localctx).type =  typeFonction;
				    
				}
				break;
			case 7:
				{
				setState(486);
				match(TRUE);
				 ((ExpressionContext)_localctx).code =  "PUSHI 1\n"; ((ExpressionContext)_localctx).type =  "bool"; 
				}
				break;
			case 8:
				{
				setState(488);
				match(FALSE);
				 ((ExpressionContext)_localctx).code =  "PUSHI 0\n"; ((ExpressionContext)_localctx).type =  "bool"; 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(504);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(502);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(492);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(493);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__32 || _la==T__33) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(494);
						((ExpressionContext)_localctx).b = ((ExpressionContext)_localctx).expression = expression(9);

						                  printErrorIfBool(((ExpressionContext)_localctx).a.type, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));//Emits an error if a is a boolean expression as we
						                  //cannot multiply or divide a boolean
						                  printErrorIfBool(((ExpressionContext)_localctx).b.type, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));//Same for b

						                  //On ne peut pas avoir de bool à partir d'ici
						                  if(!equalsType(((ExpressionContext)_localctx).a.type, ((ExpressionContext)_localctx).b.type))
						                  {
						                      ((ExpressionContext)_localctx).code =  expressionImplicitCast(((ExpressionContext)_localctx).a.code, ((ExpressionContext)_localctx).a.type, ((ExpressionContext)_localctx).b.code, ((ExpressionContext)_localctx).b.type);
						                      _localctx.code += ((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("*") ? "FMUL" : "FDIV") + "\n";

						                      ((ExpressionContext)_localctx).type =  "double";
						                  }
						                  else
						                  {
						                      ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + (((ExpressionContext)_localctx).a.type.contains("double") ? "F" : "") + ((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("*") ? "MUL" : "DIV") + "\n";

						                      ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).a.type;//= $b.type donnerait la même chose puisque a.type = b.type ici
						                  }
						              
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(497);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(498);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__31 || _la==T__34) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(499);
						((ExpressionContext)_localctx).b = ((ExpressionContext)_localctx).expression = expression(8);

						                  printErrorIfBool(((ExpressionContext)_localctx).a.type, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));//Emits an error if a is a boolean expression as we
						                  //cannot add or substract a boolean
						                  printErrorIfBool(((ExpressionContext)_localctx).b.type, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null));//Same for b

						                  if(!equalsType(((ExpressionContext)_localctx).a.type, ((ExpressionContext)_localctx).b.type))
						                  {
						                      ((ExpressionContext)_localctx).code =  expressionImplicitCast(((ExpressionContext)_localctx).a.code, ((ExpressionContext)_localctx).a.type, ((ExpressionContext)_localctx).b.code, ((ExpressionContext)_localctx).b.type);
						                      _localctx.code += ((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("+") ? "FADD" : "FSUB") + "\n";

						                      ((ExpressionContext)_localctx).type =  "double";
						                  }
						                  else
						                  {
						                      ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + (((ExpressionContext)_localctx).a.type.contains("double") ? "F" : "") + ((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null).equals("+") ? "ADD" : "SUB") + "\n";

						                      ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).a.type;//= $b.type donnerait la même chose puisque a.type = b.type ici
						                  }
						              
						}
						break;
					}
					} 
				}
				setState(506);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(MVaPCodeGeneratorParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(MVaPCodeGeneratorParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).enterFinInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MVaPCodeGeneratorListener ) ((MVaPCodeGeneratorListener)listener).exitFinInstruction(this);
		}
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(508); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(507);
					_la = _input.LA(1);
					if ( !(_la==T__23 || _la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(510); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		case 16:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66\u0203\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3\7"+
		"\3\64\n\3\f\3\16\3\67\13\3\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\3\3"+
		"\7\3C\n\3\f\3\16\3F\13\3\3\3\3\3\3\3\3\3\7\3L\n\3\f\3\16\3O\13\3\3\3\7"+
		"\3R\n\3\f\3\16\3U\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5"+
		"\4t\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0080\n\4\f\4\16\4"+
		"\u0083\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u009f\n\5\3\6\3\6\3"+
		"\6\3\6\3\6\5\6\u00a6\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00af\n\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00b8\n\6\3\6\3\6\3\6\3\6\5\6\u00be\n\6\3"+
		"\7\3\7\3\7\3\7\3\7\7\7\u00c5\n\7\f\7\16\7\u00c8\13\7\3\7\3\7\7\7\u00cc"+
		"\n\7\f\7\16\7\u00cf\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u00d8\n\b\f\b"+
		"\16\b\u00db\13\b\3\b\3\b\7\b\u00df\n\b\f\b\16\b\u00e2\13\b\3\b\3\b\7\b"+
		"\u00e6\n\b\f\b\16\b\u00e9\13\b\3\b\3\b\7\b\u00ed\n\b\f\b\16\b\u00f0\13"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00f9\n\b\f\b\16\b\u00fc\13\b\3\b\3"+
		"\b\7\b\u0100\n\b\f\b\16\b\u0103\13\b\3\b\3\b\5\b\u0107\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0112\n\t\f\t\16\t\u0115\13\t\3\t\3\t\7\t"+
		"\u0119\n\t\f\t\16\t\u011c\13\t\3\t\3\t\3\n\3\n\7\n\u0122\n\n\f\n\16\n"+
		"\u0125\13\n\3\n\3\n\7\n\u0129\n\n\f\n\16\n\u012c\13\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\n\u0134\n\n\f\n\16\n\u0137\13\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0147\n\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0169\n\f\3\r\3\r\7\r"+
		"\u016d\n\r\f\r\16\r\u0170\13\r\3\r\3\r\3\r\7\r\u0175\n\r\f\r\16\r\u0178"+
		"\13\r\3\r\7\r\u017b\n\r\f\r\16\r\u017e\13\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\5\16\u0188\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0191"+
		"\n\17\3\17\3\17\7\17\u0195\n\17\f\17\16\17\u0198\13\17\3\17\3\17\7\17"+
		"\u019c\n\17\f\17\16\17\u019f\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u01ab\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u01b7\n\20\f\20\16\20\u01ba\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u01c2\n\21\f\21\16\21\u01c5\13\21\5\21\u01c7\n\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01e0\n\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01ed\n\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u01f9\n\22\f\22\16\22\u01fc\13"+
		"\22\3\23\6\23\u01ff\n\23\r\23\16\23\u0200\3\23\2\4\6\"\24\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$\2\n\3\2\6\b\3\2\t\f\4\2\21\21\26\31\4"+
		"\2  &&\3\2\64\65\3\2#$\4\2\"\"%%\4\2\32\32\63\63\2\u0233\2&\3\2\2\2\4"+
		"/\3\2\2\2\6s\3\2\2\2\b\u009e\3\2\2\2\n\u00bd\3\2\2\2\f\u00bf\3\2\2\2\16"+
		"\u0106\3\2\2\2\20\u0108\3\2\2\2\22\u011f\3\2\2\2\24\u0146\3\2\2\2\26\u0168"+
		"\3\2\2\2\30\u016a\3\2\2\2\32\u0187\3\2\2\2\34\u0189\3\2\2\2\36\u01aa\3"+
		"\2\2\2 \u01c6\3\2\2\2\"\u01ec\3\2\2\2$\u01fe\3\2\2\2&\'\5\4\3\2\'(\7\2"+
		"\2\3()\b\2\1\2)\3\3\2\2\2*+\5\b\5\2+,\b\3\1\2,.\3\2\2\2-*\3\2\2\2.\61"+
		"\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\65\3\2\2\2\61/\3\2\2\2\62\64\7\63\2"+
		"\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2"+
		"\67\65\3\2\2\28>\b\3\1\29:\5\34\17\2:;\b\3\1\2;=\3\2\2\2<9\3\2\2\2=@\3"+
		"\2\2\2><\3\2\2\2>?\3\2\2\2?D\3\2\2\2@>\3\2\2\2AC\7\63\2\2BA\3\2\2\2CF"+
		"\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GM\b\3\1\2HI\5\26\f\2"+
		"IJ\b\3\1\2JL\3\2\2\2KH\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NS\3\2\2\2"+
		"OM\3\2\2\2PR\7\63\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2"+
		"\2US\3\2\2\2VW\b\3\1\2W\5\3\2\2\2XY\b\4\1\2YZ\7\3\2\2Z[\5\6\4\2[\\\7\4"+
		"\2\2\\]\b\4\1\2]t\3\2\2\2^_\7\5\2\2_`\5\6\4\n`a\b\4\1\2at\3\2\2\2bc\5"+
		"\"\22\2cd\t\2\2\2de\5\"\22\2ef\b\4\1\2ft\3\2\2\2gh\5\"\22\2hi\t\3\2\2"+
		"ij\5\"\22\2jk\b\4\1\2kt\3\2\2\2lm\5\"\22\2mn\b\4\1\2nt\3\2\2\2op\7.\2"+
		"\2pt\b\4\1\2qr\7/\2\2rt\b\4\1\2sX\3\2\2\2s^\3\2\2\2sb\3\2\2\2sg\3\2\2"+
		"\2sl\3\2\2\2so\3\2\2\2sq\3\2\2\2t\u0081\3\2\2\2uv\f\7\2\2vw\7\r\2\2wx"+
		"\5\6\4\bxy\b\4\1\2y\u0080\3\2\2\2z{\f\6\2\2{|\7\16\2\2|}\5\6\4\7}~\b\4"+
		"\1\2~\u0080\3\2\2\2\177u\3\2\2\2\177z\3\2\2\2\u0080\u0083\3\2\2\2\u0081"+
		"\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\7\3\2\2\2\u0083\u0081\3\2\2\2\u0084"+
		"\u0085\7\17\2\2\u0085\u0086\7\60\2\2\u0086\u0087\7\20\2\2\u0087\u0088"+
		"\7&\2\2\u0088\u0089\5$\23\2\u0089\u008a\b\5\1\2\u008a\u009f\3\2\2\2\u008b"+
		"\u008c\7\17\2\2\u008c\u008d\7\60\2\2\u008d\u008e\7\20\2\2\u008e\u008f"+
		"\7&\2\2\u008f\u0090\7\21\2\2\u0090\u0091\5\"\22\2\u0091\u0092\5$\23\2"+
		"\u0092\u0093\b\5\1\2\u0093\u009f\3\2\2\2\u0094\u0095\7\17\2\2\u0095\u0096"+
		"\7\60\2\2\u0096\u0097\7\20\2\2\u0097\u0098\7&\2\2\u0098\u0099\7\22\2\2"+
		"\u0099\u009a\7\64\2\2\u009a\u009b\7\23\2\2\u009b\u009c\5$\23\2\u009c\u009d"+
		"\b\5\1\2\u009d\u009f\3\2\2\2\u009e\u0084\3\2\2\2\u009e\u008b\3\2\2\2\u009e"+
		"\u0094\3\2\2\2\u009f\t\3\2\2\2\u00a0\u00a5\7\60\2\2\u00a1\u00a2\7\22\2"+
		"\2\u00a2\u00a3\5\"\22\2\u00a3\u00a4\7\23\2\2\u00a4\u00a6\3\2\2\2\u00a5"+
		"\u00a1\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\7\24"+
		"\2\2\u00a8\u00be\b\6\1\2\u00a9\u00ae\7\60\2\2\u00aa\u00ab\7\22\2\2\u00ab"+
		"\u00ac\5\"\22\2\u00ac\u00ad\7\23\2\2\u00ad\u00af\3\2\2\2\u00ae\u00aa\3"+
		"\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\25\2\2\u00b1"+
		"\u00be\b\6\1\2\u00b2\u00b7\7\60\2\2\u00b3\u00b4\7\22\2\2\u00b4\u00b5\5"+
		"\"\22\2\u00b5\u00b6\7\23\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\t\4\2\2\u00ba\u00bb\5\""+
		"\22\2\u00bb\u00bc\b\6\1\2\u00bc\u00be\3\2\2\2\u00bd\u00a0\3\2\2\2\u00bd"+
		"\u00a9\3\2\2\2\u00bd\u00b2\3\2\2\2\u00be\13\3\2\2\2\u00bf\u00c0\7\'\2"+
		"\2\u00c0\u00c1\7\3\2\2\u00c1\u00c2\5\6\4\2\u00c2\u00c6\7\4\2\2\u00c3\u00c5"+
		"\7\63\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2"+
		"\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00cd"+
		"\5\32\16\2\u00ca\u00cc\7\63\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2"+
		"\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd"+
		"\3\2\2\2\u00d0\u00d1\b\7\1\2\u00d1\r\3\2\2\2\u00d2\u00d3\7+\2\2\u00d3"+
		"\u00d4\7\3\2\2\u00d4\u00d5\5\6\4\2\u00d5\u00d9\7\4\2\2\u00d6\u00d8\7\63"+
		"\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00e0\5\32"+
		"\16\2\u00dd\u00df\7\63\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2"+
		"\2\2\u00e3\u00e7\7,\2\2\u00e4\u00e6\7\63\2\2\u00e5\u00e4\3\2\2\2\u00e6"+
		"\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00ea\3\2"+
		"\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00ee\5\32\16\2\u00eb\u00ed\7\63\2\2\u00ec"+
		"\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2"+
		"\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\b\b\1\2\u00f2"+
		"\u0107\3\2\2\2\u00f3\u00f4\7+\2\2\u00f4\u00f5\7\3\2\2\u00f5\u00f6\5\6"+
		"\4\2\u00f6\u00fa\7\4\2\2\u00f7\u00f9\7\63\2\2\u00f8\u00f7\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fd\u0101\5\32\16\2\u00fe\u0100\7\63\2\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0105\b\b\1\2\u0105"+
		"\u0107\3\2\2\2\u0106\u00d2\3\2\2\2\u0106\u00f3\3\2\2\2\u0107\17\3\2\2"+
		"\2\u0108\u0109\7(\2\2\u0109\u010a\7\3\2\2\u010a\u010b\5\n\6\2\u010b\u010c"+
		"\7\32\2\2\u010c\u010d\5\6\4\2\u010d\u010e\7\32\2\2\u010e\u010f\5\n\6\2"+
		"\u010f\u0113\7\4\2\2\u0110\u0112\7\63\2\2\u0111\u0110\3\2\2\2\u0112\u0115"+
		"\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0116\u011a\5\32\16\2\u0117\u0119\7\63\2\2\u0118\u0117"+
		"\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b"+
		"\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e\b\t\1\2\u011e\21\3\2\2"+
		"\2\u011f\u0123\7)\2\2\u0120\u0122\7\63\2\2\u0121\u0120\3\2\2\2\u0122\u0125"+
		"\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0126\u012a\5\32\16\2\u0127\u0129\7\63\2\2\u0128\u0127"+
		"\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b"+
		"\u012d\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012e\7*\2\2\u012e\u012f\7\3"+
		"\2\2\u012f\u0130\5\6\4\2\u0130\u0131\7\4\2\2\u0131\u0135\5$\23\2\u0132"+
		"\u0134\7\63\2\2\u0133\u0132\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3"+
		"\2\2\2\u0135\u0136\3\2\2\2\u0136\u0138\3\2\2\2\u0137\u0135\3\2\2\2\u0138"+
		"\u0139\b\n\1\2\u0139\23\3\2\2\2\u013a\u013b\5\f\7\2\u013b\u013c\b\13\1"+
		"\2\u013c\u0147\3\2\2\2\u013d\u013e\5\16\b\2\u013e\u013f\b\13\1\2\u013f"+
		"\u0147\3\2\2\2\u0140\u0141\5\20\t\2\u0141\u0142\b\13\1\2\u0142\u0147\3"+
		"\2\2\2\u0143\u0144\5\22\n\2\u0144\u0145\b\13\1\2\u0145\u0147\3\2\2\2\u0146"+
		"\u013a\3\2\2\2\u0146\u013d\3\2\2\2\u0146\u0140\3\2\2\2\u0146\u0143\3\2"+
		"\2\2\u0147\25\3\2\2\2\u0148\u0149\5\24\13\2\u0149\u014a\b\f\1\2\u014a"+
		"\u0169\3\2\2\2\u014b\u014c\5\"\22\2\u014c\u014d\5$\23\2\u014d\u014e\b"+
		"\f\1\2\u014e\u0169\3\2\2\2\u014f\u0150\5\n\6\2\u0150\u0151\5$\23\2\u0151"+
		"\u0152\b\f\1\2\u0152\u0169\3\2\2\2\u0153\u0154\7\32\2\2\u0154\u0169\b"+
		"\f\1\2\u0155\u0156\7\33\2\2\u0156\u0157\7\3\2\2\u0157\u0158\7\60\2\2\u0158"+
		"\u0159\7\4\2\2\u0159\u015a\5$\23\2\u015a\u015b\b\f\1\2\u015b\u0169\3\2"+
		"\2\2\u015c\u015d\7\34\2\2\u015d\u015e\7\3\2\2\u015e\u015f\5\"\22\2\u015f"+
		"\u0160\7\4\2\2\u0160\u0161\5$\23\2\u0161\u0162\b\f\1\2\u0162\u0169\3\2"+
		"\2\2\u0163\u0164\7-\2\2\u0164\u0165\5\"\22\2\u0165\u0166\5$\23\2\u0166"+
		"\u0167\b\f\1\2\u0167\u0169\3\2\2\2\u0168\u0148\3\2\2\2\u0168\u014b\3\2"+
		"\2\2\u0168\u014f\3\2\2\2\u0168\u0153\3\2\2\2\u0168\u0155\3\2\2\2\u0168"+
		"\u015c\3\2\2\2\u0168\u0163\3\2\2\2\u0169\27\3\2\2\2\u016a\u016e\7\35\2"+
		"\2\u016b\u016d\7\63\2\2\u016c\u016b\3\2\2\2\u016d\u0170\3\2\2\2\u016e"+
		"\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0176\3\2\2\2\u0170\u016e\3\2"+
		"\2\2\u0171\u0172\5\26\f\2\u0172\u0173\b\r\1\2\u0173\u0175\3\2\2\2\u0174"+
		"\u0171\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2"+
		"\2\2\u0177\u017c\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u017b\7\63\2\2\u017a"+
		"\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2"+
		"\2\2\u017d\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0180\7\36\2\2\u0180"+
		"\31\3\2\2\2\u0181\u0182\5\26\f\2\u0182\u0183\b\16\1\2\u0183\u0188\3\2"+
		"\2\2\u0184\u0185\5\30\r\2\u0185\u0186\b\16\1\2\u0186\u0188\3\2\2\2\u0187"+
		"\u0181\3\2\2\2\u0187\u0184\3\2\2\2\u0188\33\3\2\2\2\u0189\u018a\7\37\2"+
		"\2\u018a\u018b\7\60\2\2\u018b\u018c\7\20\2\2\u018c\u018d\t\5\2\2\u018d"+
		"\u018e\b\17\1\2\u018e\u0190\7\3\2\2\u018f\u0191\5\36\20\2\u0190\u018f"+
		"\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0196\7\4\2\2\u0193"+
		"\u0195\7\63\2\2\u0194\u0193\3\2\2\2\u0195\u0198\3\2\2\2\u0196\u0194\3"+
		"\2\2\2\u0196\u0197\3\2\2\2\u0197\u0199\3\2\2\2\u0198\u0196\3\2\2\2\u0199"+
		"\u019d\5\30\r\2\u019a\u019c\7\63\2\2\u019b\u019a\3\2\2\2\u019c\u019f\3"+
		"\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3\2\2\2\u019f"+
		"\u019d\3\2\2\2\u01a0\u01a1\b\17\1\2\u01a1\35\3\2\2\2\u01a2\u01a3\7&\2"+
		"\2\u01a3\u01a4\7\60\2\2\u01a4\u01ab\b\20\1\2\u01a5\u01a6\7&\2\2\u01a6"+
		"\u01a7\7\60\2\2\u01a7\u01a8\7\22\2\2\u01a8\u01a9\7\23\2\2\u01a9\u01ab"+
		"\b\20\1\2\u01aa\u01a2\3\2\2\2\u01aa\u01a5\3\2\2\2\u01ab\u01b8\3\2\2\2"+
		"\u01ac\u01ad\7!\2\2\u01ad\u01ae\7&\2\2\u01ae\u01af\7\60\2\2\u01af\u01b7"+
		"\b\20\1\2\u01b0\u01b1\7!\2\2\u01b1\u01b2\7&\2\2\u01b2\u01b3\7\60\2\2\u01b3"+
		"\u01b4\7\22\2\2\u01b4\u01b5\7\23\2\2\u01b5\u01b7\b\20\1\2\u01b6\u01ac"+
		"\3\2\2\2\u01b6\u01b0\3\2\2\2\u01b7\u01ba\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8"+
		"\u01b9\3\2\2\2\u01b9\37\3\2\2\2\u01ba\u01b8\3\2\2\2\u01bb\u01bc\5\"\22"+
		"\2\u01bc\u01c3\b\21\1\2\u01bd\u01be\7!\2\2\u01be\u01bf\5\"\22\2\u01bf"+
		"\u01c0\b\21\1\2\u01c0\u01c2\3\2\2\2\u01c1\u01bd\3\2\2\2\u01c2\u01c5\3"+
		"\2\2\2\u01c3\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c7\3\2\2\2\u01c5"+
		"\u01c3\3\2\2\2\u01c6\u01bb\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7!\3\2\2\2"+
		"\u01c8\u01c9\b\22\1\2\u01c9\u01ca\7\3\2\2\u01ca\u01cb\5\"\22\2\u01cb\u01cc"+
		"\7\4\2\2\u01cc\u01cd\b\22\1\2\u01cd\u01ed\3\2\2\2\u01ce\u01cf\7\"\2\2"+
		"\u01cf\u01d0\5\"\22\13\u01d0\u01d1\b\22\1\2\u01d1\u01ed\3\2\2\2\u01d2"+
		"\u01d3\7\3\2\2\u01d3\u01d4\7&\2\2\u01d4\u01d5\7\4\2\2\u01d5\u01d6\5\""+
		"\22\b\u01d6\u01d7\b\22\1\2\u01d7\u01ed\3\2\2\2\u01d8\u01d9\t\6\2\2\u01d9"+
		"\u01ed\b\22\1\2\u01da\u01df\7\60\2\2\u01db\u01dc\7\22\2\2\u01dc\u01dd"+
		"\5\"\22\2\u01dd\u01de\7\23\2\2\u01de\u01e0\3\2\2\2\u01df\u01db\3\2\2\2"+
		"\u01df\u01e0\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01ed\b\22\1\2\u01e2\u01e3"+
		"\7\60\2\2\u01e3\u01e4\7\3\2\2\u01e4\u01e5\5 \21\2\u01e5\u01e6\7\4\2\2"+
		"\u01e6\u01e7\b\22\1\2\u01e7\u01ed\3\2\2\2\u01e8\u01e9\7.\2\2\u01e9\u01ed"+
		"\b\22\1\2\u01ea\u01eb\7/\2\2\u01eb\u01ed\b\22\1\2\u01ec\u01c8\3\2\2\2"+
		"\u01ec\u01ce\3\2\2\2\u01ec\u01d2\3\2\2\2\u01ec\u01d8\3\2\2\2\u01ec\u01da"+
		"\3\2\2\2\u01ec\u01e2\3\2\2\2\u01ec\u01e8\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ed"+
		"\u01fa\3\2\2\2\u01ee\u01ef\f\n\2\2\u01ef\u01f0\t\7\2\2\u01f0\u01f1\5\""+
		"\22\13\u01f1\u01f2\b\22\1\2\u01f2\u01f9\3\2\2\2\u01f3\u01f4\f\t\2\2\u01f4"+
		"\u01f5\t\b\2\2\u01f5\u01f6\5\"\22\n\u01f6\u01f7\b\22\1\2\u01f7\u01f9\3"+
		"\2\2\2\u01f8\u01ee\3\2\2\2\u01f8\u01f3\3\2\2\2\u01f9\u01fc\3\2\2\2\u01fa"+
		"\u01f8\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb#\3\2\2\2\u01fc\u01fa\3\2\2\2"+
		"\u01fd\u01ff\t\t\2\2\u01fe\u01fd\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u01fe"+
		"\3\2\2\2\u0200\u0201\3\2\2\2\u0201%\3\2\2\2\61/\65>DMSs\177\u0081\u009e"+
		"\u00a5\u00ae\u00b7\u00bd\u00c6\u00cd\u00d9\u00e0\u00e7\u00ee\u00fa\u0101"+
		"\u0106\u0113\u011a\u0123\u012a\u0135\u0146\u0168\u016e\u0176\u017c\u0187"+
		"\u0190\u0196\u019d\u01aa\u01b6\u01b8\u01c3\u01c6\u01df\u01ec\u01f8\u01fa"+
		"\u0200";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}