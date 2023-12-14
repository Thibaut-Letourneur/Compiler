// Generated from MVaPCodeGenerator.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MVaPCodeGeneratorParser}.
 */
public interface MVaPCodeGeneratorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(MVaPCodeGeneratorParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(MVaPCodeGeneratorParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#calcul}.
	 * @param ctx the parse tree
	 */
	void enterCalcul(MVaPCodeGeneratorParser.CalculContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#calcul}.
	 * @param ctx the parse tree
	 */
	void exitCalcul(MVaPCodeGeneratorParser.CalculContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(MVaPCodeGeneratorParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(MVaPCodeGeneratorParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MVaPCodeGeneratorParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MVaPCodeGeneratorParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#assignation}.
	 * @param ctx the parse tree
	 */
	void enterAssignation(MVaPCodeGeneratorParser.AssignationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#assignation}.
	 * @param ctx the parse tree
	 */
	void exitAssignation(MVaPCodeGeneratorParser.AssignationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#whileStruct}.
	 * @param ctx the parse tree
	 */
	void enterWhileStruct(MVaPCodeGeneratorParser.WhileStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#whileStruct}.
	 * @param ctx the parse tree
	 */
	void exitWhileStruct(MVaPCodeGeneratorParser.WhileStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#ifStruct}.
	 * @param ctx the parse tree
	 */
	void enterIfStruct(MVaPCodeGeneratorParser.IfStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#ifStruct}.
	 * @param ctx the parse tree
	 */
	void exitIfStruct(MVaPCodeGeneratorParser.IfStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#forStruct}.
	 * @param ctx the parse tree
	 */
	void enterForStruct(MVaPCodeGeneratorParser.ForStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#forStruct}.
	 * @param ctx the parse tree
	 */
	void exitForStruct(MVaPCodeGeneratorParser.ForStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#repeatUntilStruct}.
	 * @param ctx the parse tree
	 */
	void enterRepeatUntilStruct(MVaPCodeGeneratorParser.RepeatUntilStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#repeatUntilStruct}.
	 * @param ctx the parse tree
	 */
	void exitRepeatUntilStruct(MVaPCodeGeneratorParser.RepeatUntilStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(MVaPCodeGeneratorParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(MVaPCodeGeneratorParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(MVaPCodeGeneratorParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(MVaPCodeGeneratorParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#bloc}.
	 * @param ctx the parse tree
	 */
	void enterBloc(MVaPCodeGeneratorParser.BlocContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#bloc}.
	 * @param ctx the parse tree
	 */
	void exitBloc(MVaPCodeGeneratorParser.BlocContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#instructionOrBloc}.
	 * @param ctx the parse tree
	 */
	void enterInstructionOrBloc(MVaPCodeGeneratorParser.InstructionOrBlocContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#instructionOrBloc}.
	 * @param ctx the parse tree
	 */
	void exitInstructionOrBloc(MVaPCodeGeneratorParser.InstructionOrBlocContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#fonction}.
	 * @param ctx the parse tree
	 */
	void enterFonction(MVaPCodeGeneratorParser.FonctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#fonction}.
	 * @param ctx the parse tree
	 */
	void exitFonction(MVaPCodeGeneratorParser.FonctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(MVaPCodeGeneratorParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(MVaPCodeGeneratorParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(MVaPCodeGeneratorParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(MVaPCodeGeneratorParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MVaPCodeGeneratorParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MVaPCodeGeneratorParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MVaPCodeGeneratorParser#finInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFinInstruction(MVaPCodeGeneratorParser.FinInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MVaPCodeGeneratorParser#finInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFinInstruction(MVaPCodeGeneratorParser.FinInstructionContext ctx);
}