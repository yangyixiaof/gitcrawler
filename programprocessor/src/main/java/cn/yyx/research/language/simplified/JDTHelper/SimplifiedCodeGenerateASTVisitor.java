package cn.yyx.research.language.simplified.JDTHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.*;

import cn.yyx.research.language.JDTManager.FirstOrderTask;
import cn.yyx.research.language.JDTManager.FirstOrderTaskPool;
import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.JCScope;
import cn.yyx.research.language.JDTManager.MethodWindow;
import cn.yyx.research.language.JDTManager.NodeCode;
import cn.yyx.research.language.JDTManager.NodeHelpManager;
import cn.yyx.research.language.JDTManager.OffsetLibrary;
import cn.yyx.research.language.JDTManager.OneJavaFileCode;
import cn.yyx.research.language.JDTManager.OtherCodeManager;
import cn.yyx.research.language.JDTManager.ReferenceHintLibrary;
import cn.yyx.research.language.JDTManager.ScopeDataManager;
import cn.yyx.research.language.Utility.CDType;
import cn.yyx.research.language.Utility.MyLogger;
import cn.yyx.research.language.Utility.StringUtil;
import cn.yyx.research.language.programprocessor.RunMetaInfo;
import cn.yyx.research.language.simplified.JDTManager.AnonymousClassPoolInOneJavaFile;
import cn.yyx.research.language.simplified.JDTManager.ConflictASTNodeHashCodeError;
import cn.yyx.research.language.simplified.JDTManager.ContentsAndWords;
import cn.yyx.research.language.simplified.JDTManager.ForBlockState;
import cn.yyx.research.language.simplified.JDTManager.JavaCode;
import cn.yyx.research.language.simplified.JDTManager.ScopeOffsetRefHandler;
import cn.yyx.research.language.simplified.JDTManager.TypeASTHelper;
import cn.yyx.research.language.simplified.JDTManager.TypeUtil;

public class SimplifiedCodeGenerateASTVisitor extends ASTVisitor {

    protected OtherCodeManager ocm = new OtherCodeManager();
    protected OneJavaFileCode ojfc = new OneJavaFileCode();
    protected JavaCode jc = ojfc;
    protected Stack<MethodWindow> mw = new Stack<MethodWindow>();
    protected AnonymousClassPoolInOneJavaFile acp = new AnonymousClassPoolInOneJavaFile();
    protected FirstOrderTaskPool fotp = new FirstOrderTaskPool();
    protected ScopeDataManager sdm = new ScopeDataManager();
    protected JCScope cjcs = new JCScope();
    protected JCScope ljcs = new JCScope();
    protected Integer FirstLevelClass = null;
    protected Stack<CDType> VeryRecentDeclaredType = new Stack<CDType>();
    protected NodeHelpManager<Boolean> fielddeclared = new NodeHelpManager<Boolean>();
    protected NodeHelpManager<Boolean> berefered = new NodeHelpManager<Boolean>();
    protected NodeHelpManager<String> referedcnt = new NodeHelpManager<String>();
    protected NodeHelpManager<Integer> referhint = new NodeHelpManager<Integer>();
    protected NodeHelpManager<Boolean> refernoline = new NodeHelpManager<Boolean>();
    protected NodeHelpManager<Boolean> runpermit = new NodeHelpManager<Boolean>();
    protected NodeHelpManager<Boolean> runforbid = new NodeHelpManager<Boolean>();
    protected NodeHelpManager<Boolean> typesimp = new NodeHelpManager<Boolean>();
    protected NodeHelpManager<Integer> dostmtln = new NodeHelpManager<Integer>();
    protected Map<Integer, Boolean> scopeck = new TreeMap<Integer, Boolean>();
    protected Stack<NodeCode> omcanonystack = new Stack<NodeCode>();
    protected Stack<Boolean> argmutiple = new Stack<Boolean>();
    protected NodeCode omc = new NodeCode(argmutiple);

    // type just use the last element.
    // public static final int StrictedTypeLength = 2;

    {
        cjcs.SetDescription("Class Declaration.");
        ljcs.SetDescription("Label Declaration.");
    }

    public ScopeOffsetRefHandler GenerateScopeOffsetRefHandler() {
        return new ScopeOffsetRefHandler(sdm.GetClassStack(), sdm.GetFVDataPool(), sdm.GetCVDataPool(), cjcs, ljcs);
    }

    @Override
    public boolean preVisit2(ASTNode node) {
        fotp.PostIsBegin(node);
        boolean fres = true;
        Boolean forbid = runforbid.GetNodeHelp(node.hashCode());
        if (forbid != null && forbid == true) {
            fres = fres && false;
        }
        if (NeedSpecialTreat(node) && fres) {
            EnterBlock(node);
        }
        return fres;
    }

    @Override
    public void postVisit(ASTNode node) {
        fotp.PreIsOver(node);
        if (NeedSpecialTreat(node)) {
            ExitBlock(node);
        }
    }

    @Override
    public boolean visit(ImportDeclaration node) {
        return false;
    }

    @Override
    public boolean visit(PackageDeclaration node) {
        return false;
    }

    @Override
    public boolean visit(SingleMemberAnnotation node) {
        return false;
    }

    @Override
    public boolean visit(NormalAnnotation node) {
        return false;
    }

    @Override
    public boolean visit(MarkerAnnotation node) {
        return false;
    }

    @Override
    public boolean visit(ThisExpression node) {
        QualifiedPreHandle(node, node.getQualifier(), null);
        return false;
    }

    @Override
    public void endVisit(ThisExpression node) {
        QualifiedPostHandle(node, node.getQualifier(), "this", null, null, null, true);
        super.endVisit(node);
    }

    @Override
    public boolean visit(TagElement node) {
        return false;
    }

    @Override
    public boolean visit(Modifier node) {
        return false;
    }

    @Override
    public boolean visit(MethodRefParameter node) {
        return false;
    }

    @Override
    public boolean visit(MethodRef node) {
        return false;
    }

    @Override
    public boolean visit(MemberValuePair node) {
        return false;
    }

    @Override
    public boolean visit(MemberRef node) {
        return false;
    }

    @Override
    public boolean visit(LineComment node) {
        return false;
    }

    @Override
    public boolean visit(Javadoc node) {
        return false;
    }

    @Override
    public boolean visit(EmptyStatement node) {
        return false;
    }

    @Override
    public boolean visit(TextElement node) {
        return false;
    }

    @Override
    public boolean visit(Dimension node) {
        return false;
    }

    @Override
    public boolean visit(CompilationUnit node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(BlockComment node) {
        return false;
    }

    @Override
    public boolean visit(AssertStatement node) {
        return false;
    }

    @Override
    public boolean visit(TryStatement node) {
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + "try", false, false, false, true, null);
        ASTNode lastbeforefinally = node.getBody();
        if (node.catchClauses() != null && node.catchClauses().size() > 0) {
            lastbeforefinally = (ASTNode) node.catchClauses().get(node.catchClauses().size() - 1);
        }
        if (node.getFinally() != null) {
            AddFirstOrderTask(new FirstOrderTask(lastbeforefinally, null, node, true, false) {
                @Override
                public void run() {
                    GenerateOneLine(GCodeMetaInfo.DescriptionHint + "finally", false, false, false, true, null);
                }
            });
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclarationStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeParameter node) {
        return false;
    }

    // raw string
    @Override
    public boolean visit(EnumDeclaration node) {
        // Do nothing now.
        // MyLogger.Info("EnumDeclaration:"+node);
        AppendOtherCode(GCodeMetaInfo.EnumCorpus, node.getName().toString());
        boolean ifcontinue = TypeDeclarationPreCode(node, GCodeMetaInfo.EnumDeclarationHint);
        OnlyVisitFieldDeclaration(node, ifcontinue);
        return ifcontinue && super.visit(node);
    }

    @Override
    public void endVisit(EnumDeclaration node) {
        TypeDeclarationPostCode(node);
    }

    @Override
    public boolean visit(AnnotationTypeDeclaration node) {
        boolean ifcontinue = TypeDeclarationPreCode(node, GCodeMetaInfo.ATInterfaceHint);
        OnlyVisitFieldDeclaration(node, ifcontinue);
        return ifcontinue && super.visit(node);
    }

    @Override
    public void endVisit(AnnotationTypeDeclaration node) {
        TypeDeclarationPostCode(node);
    }

    @Override
    public boolean visit(AnnotationTypeMemberDeclaration node) {
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        Expression expr = node.getDefault();
        if (expr != null) {
            ExpressionReferPreHandle(expr, ReferenceHintLibrary.DataUse);
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(AnnotationTypeMemberDeclaration node) {
        String onecode = TypeUtil.TypeCode(node.getType(), true) + GCodeMetaInfo.WhiteSpaceReplacer + node.getName().toString()
                + "()";
        Expression expr = node.getDefault();
        if (expr != null) {
            ExpressionReferPostHandle(node, expr, null, GCodeMetaInfo.AnnotationTypeMemberDeclarationHint,
                    onecode + "default", false, true, false, false, false);
        } else {
            GenerateOneLine(onecode, false, false, false, true, GCodeMetaInfo.AnnotationTypeMemberDeclarationHint);
        }
        runforbid.DeleteNodeHelp(node.getName().hashCode());
        GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
    }

    protected void GeneralVisitFieldDeclaration(AbstractTypeDeclaration node, boolean ifcontinue) {
        if (!ifcontinue) {
            return;
        }
        SimplifiedFieldProcessASTVisitor sfpa = GenerateSimplifiedFieldProcessASTVisitor(node);
        node.accept(sfpa);
    }

    @SuppressWarnings("unchecked")
    protected void OnlyVisitFieldDeclaration(AbstractTypeDeclaration node, boolean ifcontinue) {
        if (!ifcontinue) {
            return;
        }
        SimplifiedFieldProcessASTVisitor sfpa = GenerateSimplifiedFieldProcessASTVisitor(node);
        List<BodyDeclaration> bnlist = node.bodyDeclarations();
        Iterator<BodyDeclaration> itr = bnlist.iterator();
        while (itr.hasNext()) {
            BodyDeclaration bd = itr.next();
            if (bd instanceof FieldDeclaration) {
                bd.accept(sfpa);
            }
        }
    }

    protected SimplifiedFieldProcessASTVisitor GenerateSimplifiedFieldProcessASTVisitor(ASTNode node) {
        return new SimplifiedFieldProcessASTVisitor(this, node);
    }

    protected boolean TypeDeclarationPreCode(AbstractTypeDeclaration node, String preHint) {

        FlushCode();

        if (TypeASTHelper.IsEmptyTypeDeclaration(node)) {
            return false;
        }

        if (FirstLevelClass == null) {
            FirstLevelClass = node.hashCode();
        }
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        GenerateOneLine(preHint + node.getName().toString(), false, false, false, true, null);
        return true;
    }

    protected void TypeDeclarationPostCode(AbstractTypeDeclaration node) {
        if (TypeASTHelper.IsEmptyTypeDeclaration(node)) {
            return;
        }

        FlushCode();
        if (FirstLevelClass == node.hashCode()) {
            FirstLevelClass = null;
        }
        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        boolean inner = false;
        if (FirstLevelClass != null) {
            inner = true;
        }
        String hint = (inner ? GCodeMetaInfo.ClassInnerDeclarationHint : GCodeMetaInfo.ClassDeclarationHint);
        boolean ifcontinue = TypeDeclarationPreCode(node, hint);
        GeneralVisitFieldDeclaration(node, ifcontinue);
        return ifcontinue && super.visit(node);
    }

    @Override
    public void endVisit(TypeDeclaration node) {
        TypeDeclarationPostCode(node);
    }

    protected void EnterCodeSwitchScope(boolean useparentmw) {
        MethodWindow usemw = mw.peek();
        if (useparentmw) {
            if (mw.size() < 2) {
                System.err.println("What the fuck! mw size < 2 and use parent mw?");
                System.exit(1);
            }
            MethodWindow tm = mw.pop();
            usemw = mw.peek();
            mw.push(tm);
        }
        jc = acp.EnterAnonymousClass(usemw);
        omcanonystack.push(omc);
        omc = new NodeCode(argmutiple);
    }

    protected void ExitCodeSwitchScope(ASTNode node) {
        FlushCode();
        omc = omcanonystack.pop();
        if (omc == null) {
            omc = (new NodeCode(argmutiple));
        }
        jc = acp.ExitAnonymousClass();
        if (jc == null) {
            jc = ojfc;
        }
    }

    @Override
    public boolean visit(AnonymousClassDeclaration node) {
        // MyLogger.Info("AnonymousClassDeclaration Begin");
        // MyLogger.Info(node);
        // MyLogger.Info("AnonymousClassDeclaration End");
        EnterCodeSwitchScope(true);
        // AnonymousClassDeclarationCodeFileAddMethodWindow();
        SimplifiedFieldProcessASTVisitor sfpa = GenerateSimplifiedFieldProcessASTVisitor(node);
        node.accept(sfpa);
        return super.visit(node);
    }

    @Override
    public void endVisit(AnonymousClassDeclaration node) {
        ExitCodeSwitchScope(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(LambdaExpression node) {
        // SetVeryRecentNotGenerateCode(true);
        List<ASTNode> params = node.parameters();
        StringBuffer nodecode = new StringBuffer("");
        Iterator<ASTNode> itr = params.iterator();
        while (itr.hasNext()) {
            ASTNode para = itr.next();
            runforbid.AddNodeHelp(para.hashCode(), true);
            if (para instanceof VariableDeclarationFragment) {
                nodecode.append(GCodeMetaInfo.InferedType);
                NewVariableDeclared(((VariableDeclarationFragment) para).getName(), new CDType(GCodeMetaInfo.InferedType, GCodeMetaInfo.InferedType));
            } else {
                CDType cdt = GenCDType(((SingleVariableDeclaration) para).getType());
                nodecode.append(cdt);
                NewVariableDeclared(((SingleVariableDeclaration) para).getName(), cdt);
            }
            nodecode.append(',');
        }
        if (params.size() > 0) {
            nodecode.deleteCharAt(nodecode.length() - 1);
        }
        ASTNode body = node.getBody();
        String bodycode = GCodeMetaInfo.CodeHole;
        if (body instanceof SimpleName || body instanceof QualifiedName) {
            if (body instanceof SimpleName) {
                AddNodeRefered(body.hashCode(), ReferenceHintLibrary.DataUse);
                visit((SimpleName) body);
                bodycode = referedcnt.GetNodeHelp(body.hashCode());
                DeleteNodeRefered(body.hashCode());
                // referhint.DeleteNodeHelp();
            } else {
                bodycode = body.toString();
            }
            runforbid.AddNodeHelp(body.hashCode(), true);
        } else {
            referhint.AddNodeHelp(body.hashCode(), ReferenceHintLibrary.DataUse);
            if (body instanceof Block || body instanceof IfStatement || body instanceof WhileStatement || body instanceof ForStatement || body instanceof EnhancedForStatement) {
                bodycode = "{}";
            }
        }
        String nc = nodecode.toString();
        mw.peek().PushMethodName(nc.equals("") ? "L0LbdParaEmpty" : "L0" + nc.replace('@', '0').replace(',', '1'));
        GenerateOneLine(GCodeMetaInfo.LambdaExpressionHint + "(" + nc + ")->" + bodycode, false, false, false, true, null);
        if (body instanceof Block || body instanceof IfStatement || body instanceof WhileStatement || body instanceof ForStatement || body instanceof EnhancedForStatement) {
            EnterCodeSwitchScope(false);
        }
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(LambdaExpression node) {
        List<ASTNode> params = node.parameters();
        Iterator<ASTNode> itr = params.iterator();
        while (itr.hasNext()) {
            ASTNode para = itr.next();
            runforbid.DeleteNodeHelp(para.hashCode());
            /*
             * if (para instanceof VariableDeclarationFragment) {
             * SetVeryRecentDeclaredType(null); }
             */
        }
        ASTNode body = node.getBody();
        if (body instanceof SimpleName || body instanceof QualifiedName) {
            runforbid.DeleteNodeHelp(body.hashCode());
        } else {
            referhint.DeleteNodeHelp(body.hashCode());
            if (body instanceof Block || body instanceof IfStatement || body instanceof WhileStatement || body instanceof ForStatement || body instanceof EnhancedForStatement) {
                ExitCodeSwitchScope(node);
            } else {
                AppendEndInfoToLast(GCodeMetaInfo.EndOfLambdaExpression);
            }
        }
        // SetVeryRecentNotGenerateCode(false);
    }

    @Override
    public boolean visit(ExpressionStatement node) {
        return super.visit(node);
    }

    @Override
    public void endVisit(ExpressionStatement node) {
        Expression expr = node.getExpression();
        if (expr != null) {
            GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
            // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
        }
    }

    @Override
    public boolean visit(Initializer node) {
        // Do nothing now.
        // MyLogger.Info("Initializer:"+node);
        // if (isFirstLevelASTNode(node) || ParentIsTypeDeclaration(node)) {
        if (omc != null && !omc.IsEmpty()) {
            PushMethodNodeCodeToJavaFileCode();
            ResetDLM();
        }
        omc = new NodeCode(argmutiple);
        // }
        /*
         * if (isFirstLevelASTNode(node)) { if (omc != null && !omc.IsEmpty()) {
         * PushMethodNodeCodeToJavaFileCode(); } omc = new NodeCode(argmutiple);
         * }
         */
        GenerateOneLine(GCodeMetaInfo.Initializer + "InitialBlock", false, false, false, true, null);
        return super.visit(node);
    }

    @Override
    public void endVisit(Initializer node) {
        //if (isFirstLevelASTNode(node)) {
        FlushCode();
        //} else {
        //	OneSentenceEnd();
        //}
    }

    @Override
    public boolean visit(Block node) {
        // GenerateOneLine(GCodeMetaInfo.DescriptionHint + "{", false, false, false, true, null);
        return super.visit(node);
    }

    @Override
    public void endVisit(Block node) {
        // GenerateOneLine(GCodeMetaInfo.DescriptionHint + "}", false, false, false, true, null);
    }

    @Override
    public boolean visit(ExpressionMethodReference node) {
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        Integer hint = referhint.GetNodeHelp(node.hashCode());
        CheckHint(hint);
        ExpressionReferPreHandle(node.getExpression(), hint);
        return super.visit(node);
    }

    @Override
    public void endVisit(ExpressionMethodReference node) {
        // expression reference should be considered more carefully.
        ExpressionReferPostHandle(node, node.getExpression(), "::", GCodeMetaInfo.MethodReferenceHint,
                node.getName().toString(), false, false, false, false, false);
        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    @Override
    public boolean visit(CreationReference node) {
        Type type = node.getType();
        String nodecode = "new::" + TypeUtil.TypeCode(type, true);
        GenerateOneLine(nodecode, false, false, false, false, GCodeMetaInfo.MethodReferenceHint);
        return false;
    }

    @Override
    public boolean visit(SuperMethodReference node) {
        // MyLogger.Info("SuperMethodReference:"+node);
        // runforbid.AddNodeHelp(.hashCode(), true);
        return QualifiedPreHandle(node, node.getQualifier(), node.getName()) && super.visit(node);
    }

    @Override
    public void endVisit(SuperMethodReference node) {
        // qualified reference should be considered more carefully.
        Name qualifier = node.getQualifier();
        Name name = node.getName();
        QualifiedPostHandle(node, qualifier, name.toString(), name.hashCode(), "super", "::", true);
        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    @Override
    public boolean visit(TypeMethodReference node) {
        // MyLogger.Info("TypeMethodReference:"+node);
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        Type type = node.getType();
        String nodecode = node.getName().toString() + "::" + TypeUtil.TypeCode(type, true);
        GenerateOneLine(nodecode, false, false, false, false, GCodeMetaInfo.MethodReferenceHint);
        return false;
    }

    @Override
    public void endVisit(TypeMethodReference node) {
        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    @Override
    public boolean visit(CastExpression node) {
        Integer hint = referhint.GetNodeHelp(node.hashCode());
        // System.err.println("CastExpression:" + node);
        CheckHint(hint);
        ExpressionReferPreHandle(node.getExpression(), hint);
        return super.visit(node);
    }

    @Override
    public void endVisit(CastExpression node) {
        ExpressionReferPostHandle(node, node.getExpression(), "", GCodeMetaInfo.CastExpressionHint,
                "(" + TypeUtil.TypeCode(node.getType(), true) + ")", false, false, false, false, false);
    }

    @Override
    public boolean visit(final Assignment node) {
        ExpressionReferPreHandle(node.getLeftHandSide(), ReferenceHintLibrary.DataUpdate);
        AddFirstOrderTask(new FirstOrderTask(node.getLeftHandSide(), node.getRightHandSide(), node, true, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, (Expression) getPre(), node.getOperator().toString(),
                        GCodeMetaInfo.AssignmentHint, "", true, false, true, true, false);
                referhint.AddNodeHelp(getPost().hashCode(), ReferenceHintLibrary.DataUse);
                // ExpressionReferPreHandle(node.getRightHandSide(),
                // ReferenceHintLibrary.DataUse);
            }
        });
        return super.visit(node);
    }

    @Override
    public void endVisit(Assignment node) {
        // ExpressionReferPostHandle(node, node.getRightHandSide(), "", "", "",
        // true, false, false, false, false);
        referhint.DeleteNodeHelp(node.getRightHandSide().hashCode());
    }

    @Override
    public boolean visit(BreakStatement node) {
        LabelReferPreHandle(node.getLabel());
        return super.visit(node);
    }

    @Override
    public void endVisit(BreakStatement node) {
        LabelReferPostHandle(GCodeMetaInfo.BreakHint + "break", node.getLabel());
        GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
        // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
    }

    @Override
    public boolean visit(ContinueStatement node) {
        LabelReferPreHandle(node.getLabel());
        return super.visit(node);
    }

    @Override
    public void endVisit(ContinueStatement node) {
        LabelReferPostHandle(GCodeMetaInfo.ContinueHint + "continue", node.getLabel());
        GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
        // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
    }

    @Override
    public boolean visit(DoStatement node) {
        final Expression expr = node.getExpression();
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + "do", false, false, false, true, null);
        final Statement body = node.getBody();
        dostmtln.AddNodeHelp(node.hashCode(), RecordCurrentLastIndex());
        AddFirstOrderTask(new FirstOrderTask(body, expr, node, false, false) {
            @Override
            public void run() {
                dostmtln.AddNodeHelp(body.hashCode(), RecordCurrentLastIndex());
                ExpressionReferPreHandle(expr, ReferenceHintLibrary.DataUse);
            }
        });
        return super.visit(node);
    }

    @Override
    public void endVisit(DoStatement node) {
        ExpressionReferPostHandle(node, node.getExpression(), "while", GCodeMetaInfo.DoWhileHint, "", false, true,
                false, false, false);
        int nodehashcode = node.hashCode();
        int bodyhashcode = node.getBody().hashCode();
        int nodeline = dostmtln.GetNodeHelp(nodehashcode);
        int bodyline = dostmtln.GetNodeHelp(bodyhashcode);
        MoveLastToSpecificLine(nodeline);
        MoveSpecificLineUntilLastToBeforeSpecificLine(bodyline + 1, nodeline);
        dostmtln.DeleteNodeHelp(nodehashcode);
        dostmtln.DeleteNodeHelp(bodyhashcode);
    }

    @Override
    public boolean visit(final EnhancedForStatement node) {
        ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
        runforbid.AddNodeHelp(node.getParameter().hashCode(), true);
        AddFirstOrderTask(new FirstOrderTask(node.getExpression(), null, node, true, false) {
            @Override
            public void run() {
                String code = ExpressionReferPostHandle(node, node.getExpression(), "", "", "", false, false, false,
                        false, true);
                if (code == null) {
                    code = GCodeMetaInfo.PreExist;
                }
                String nodecode = GCodeMetaInfo.EnhancedFor + "for(" + TypeUtil.TypeCode(node.getParameter().getType(), true)
                        + ":" + code + ")";
                GenerateOneLine(nodecode, false, false, false, true, null);
                NewVariableDeclared(node.getParameter().getName(), node.getParameter().getType());
            }
        });
        return super.visit(node);
    }

    @Override
    public void endVisit(EnhancedForStatement node) {
        runforbid.DeleteNodeHelp(node.getParameter().hashCode());
    }

    @Override
    public boolean visit(final ArrayAccess node) {
        int nodehashcode = node.hashCode();
        Integer hint = referhint.GetNodeHelp(nodehashcode);
        final Expression array = node.getArray();
        Expression index = node.getIndex();
        ExpressionReferPreHandle(array, hint);
        referhint.AddNodeHelp(index.hashCode(), ReferenceHintLibrary.DataUse);
        AddFirstOrderTask(new FirstOrderTask(array, index, node, true, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, array, "", GCodeMetaInfo.ArrayAccess, "#", true, false, true, true,
                        false);
            }
        });
        return super.visit(node);
    }

    @Override
    public void endVisit(ArrayAccess node) {
        AppendEndInfoToLast(GCodeMetaInfo.EndOfArrayDeclarationIndexExpression);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(ArrayCreation node) {
        ArrayType type = node.getType();

        // MyLogger.Error("ArrayType:"+type);
        // MyLogger.Error("ArrayElementType:"+type.getElementType());

        String typecode = TypeUtil.TypeCode(type.getElementType(), true);
        GenerateOneLine(GCodeMetaInfo.ArrayCreationHint + typecode + "(new)", false, false, false, true, null);
        List<Expression> list = node.dimensions();
        Iterator<Expression> itr = list.iterator();
        while (itr.hasNext()) {
            Expression expr = itr.next();
            referhint.AddNodeHelp(expr.hashCode(), ReferenceHintLibrary.DataUse);
            AddFirstOrderTask(new FirstOrderTask(expr, null, node, true, false) {
                @Override
                public void run() {
                    AppendEndInfoToLast(GCodeMetaInfo.EndOfArrayDeclarationIndexExpression);
                }
            });
        }
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(ArrayCreation node) {
        List<Expression> list = node.dimensions();
        Iterator<Expression> itr = list.iterator();
        while (itr.hasNext()) {
            Expression expr = itr.next();
            referhint.DeleteNodeHelp(expr.hashCode());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(ArrayInitializer node) {
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.ArrayInitializerBegin, false, false, false, true,
                null);
        List<Expression> list = node.expressions();
        Iterator<Expression> itr = list.iterator();
        while (itr.hasNext()) {
            Expression expr = itr.next();
            referhint.AddNodeHelp(expr.hashCode(), ReferenceHintLibrary.DataUse);
            AddFirstOrderTask(new FirstOrderTask(expr, null, node, true, false, itr.hasNext()) {
                @Override
                public void run() {
                    if ((Boolean) getAdditionalinfo()) {
                        // AppendEndInfoToLast(GCodeMetaInfo.EndOfArrayInitializerElementExpression);
                        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.ArrayInitializerSplitComma, false,
                                false, false, true, null);
                    }
                }
            });
        }
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(ArrayInitializer node) {
        List<Expression> list = node.expressions();
        Iterator<Expression> itr = list.iterator();
        while (itr.hasNext()) {
            Expression expr = itr.next();
            referhint.DeleteNodeHelp(expr.hashCode());
        }
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.ArrayInitializerEnd, false, false, false, true,
                null);
    }

    @Override
    public boolean visit(ParenthesizedExpression node) {
        Integer hint = referhint.GetNodeHelp(node.hashCode());
        if (hint != null) {
            referhint.AddNodeHelp(node.getExpression().hashCode(), hint);
        }
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.LeftParenthese, false, false, false, true, null);
        return super.visit(node);
    }

    @Override
    public void endVisit(ParenthesizedExpression node) {
        // AppendEndInfoToLast(GCodeMetaInfo.RightParenthese);
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.RightParenthese, false, false, false, true, null);
        referhint.DeleteNodeHelp(node.getExpression().hashCode());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(final InfixExpression node) {
        // MyLogger.Info("InfixExpression:" + node);
        int nodehashcode = node.hashCode();
        final String operatorcode = node.getOperator().toString();
        final Integer hint = referhint.GetNodeHelp(nodehashcode);
        final Expression left = node.getLeftOperand();
        final Expression right = node.getRightOperand();

        if (hint == null) {
            MyLogger.Error("No hint InfixExpression:" + node);
        }

        final List<Expression> extendops = node.extendedOperands();

        ExpressionReferPreHandle(left, hint);
        // referhint.AddNodeHelp(left.hashCode(), hint);
        AddFirstOrderTask(new FirstOrderTask(left, right, node, false, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, left, operatorcode, GCodeMetaInfo.InfixExpressionHint, "", true, false,
                        true, true, false);
                // String lastomc = omc.GetLastCode();
                // MyLogger.Info("Last
                // coreferhint.AddNodeHelp(node.getExpression().hashCode(),
                // ReferenceHintLibrary.DataUse);de of OMC Node Code:" +
                // lastomc);
                if (extendops == null || extendops.size() == 0) {
                    referhint.AddNodeHelp(right.hashCode(), hint);
                } else {
                    ExpressionReferPreHandle(right, hint);
                }
            }
        });
        if (extendops != null && extendops.size() != 0) {
            Expression pre = right;
            final Expression last = extendops.get(extendops.size() - 1);
            Iterator<Expression> itr = extendops.iterator();
            while (itr.hasNext()) {
                Expression op = itr.next();
                AddFirstOrderTask(new FirstOrderTask(pre, op, node, false, false) {
                    @Override
                    public void run() {
                        ExpressionReferPostHandle(node, (Expression) getPre(), operatorcode,
                                GCodeMetaInfo.InfixExpressionHint, "", true, false, true, true, false);
                        if (getPost() == last) {
                            referhint.AddNodeHelp(getPost().hashCode(), hint);
                        } else {
                            ExpressionReferPreHandle((Expression) getPost(), hint);
                        }
                    }
                });
                pre = op;
            }
        }
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(InfixExpression node) {
        Expression right = node.getRightOperand();
        List<Expression> extendops = node.extendedOperands();
        if (extendops == null || extendops.size() == 0) {
            // MyLogger.Info("infix node:" + node);
            referhint.DeleteNodeHelp(right.hashCode());
            // ExpressionReferPostHandle(node, right, "", "", "", true, false,
            // false, false, false);
        } else {
            referhint.DeleteNodeHelp(extendops.get(extendops.size() - 1).hashCode());
            // ExpressionReferPostHandle(node, extendops.get(extendops.size() -
            // 1), "", "", "", true, false, false, false, false);
        }
    }

    @Override
    public boolean visit(InstanceofExpression node) {
        ExpressionReferPreHandle(node.getLeftOperand(), ReferenceHintLibrary.DataUse);// only
        // special
        // assigned.
        return super.visit(node);
    }

    @Override
    public void endVisit(InstanceofExpression node) {
        String typecode = TypeUtil.TypeCode(node.getRightOperand(), true);
        ClassNewlyAssigned(typecode);
        ExpressionReferPostHandle(node, node.getLeftOperand(), "instanceof", GCodeMetaInfo.InstanceofExpressionHint,
                typecode, true, true, false, false, false);
    }

    @Override
    public boolean visit(LabeledStatement node) {
        String nodecode = GCodeMetaInfo.LabelDeclarationHint + node.getLabel().toString();
        referhint.AddNodeHelp(node.getLabel().hashCode(), ReferenceHintLibrary.LabelDeclare);
        GenerateOneLine(nodecode, false, false, false, true, null);
        return super.visit(node);
    }

    @Override
    public void endVisit(LabeledStatement node) {
        // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
        referhint.DeleteNodeHelp(node.getLabel().hashCode());
    }

    @Override
    public boolean visit(PostfixExpression node) {
        ExpressionReferPreHandle(node.getOperand(), ReferenceHintLibrary.DataUpdate);
        return super.visit(node);
    }

    @Override
    public void endVisit(PostfixExpression node) {
        ExpressionReferPostHandle(node, node.getOperand(), node.getOperator().toString(),
                GCodeMetaInfo.PostfixExpressionHint, "", true, false, false, false, false);
    }

    @Override
    public boolean visit(PrefixExpression node) {
        ExpressionReferPreHandle(node.getOperand(), ReferenceHintLibrary.DataUpdate);
        return super.visit(node);
    }

    @Override
    public void endVisit(PrefixExpression node) {
        ExpressionReferPostHandle(node, node.getOperand(), node.getOperator().toString(),
                GCodeMetaInfo.PrefixExpressionHint, "", false, false, false, false, false);
    }

    @Override
    public boolean visit(ReturnStatement node) {
        Expression expr = node.getExpression();
        if (expr != null) {
            ExpressionReferPreHandle(expr, ReferenceHintLibrary.DataUse);
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(ReturnStatement node) {
        Expression expr = node.getExpression();
        if (expr != null) {
            ExpressionReferPostHandle(node, expr, "return", GCodeMetaInfo.ReturnHint, "", false, true, false, false,
                    false);
            GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
            // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
        } else {
            GenerateOneLine(GCodeMetaInfo.ReturnHint + "return", false, false, false, true, null);
            GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
            // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
        }
    }

    @Override
    public boolean visit(final SwitchStatement node) {
        ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
        AddFirstOrderTask(new FirstOrderTask(node.getExpression(), null, node, true, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, node.getExpression(), "switch", GCodeMetaInfo.SwitchHint, "", false,
                        true, false, false, false);
            }
        });
        return super.visit(node);
    }

    @Override
    public void endVisit(SwitchStatement node) {
    }

    @Override
    public boolean visit(SwitchCase node) {
        Expression expr = node.getExpression();
        if (expr != null) {
            ExpressionReferPreHandle(expr, ReferenceHintLibrary.DataUse);
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(SwitchCase node) {
        Expression expr = node.getExpression();
        if (expr != null) {
            ExpressionReferPostHandle(node, expr, "case", GCodeMetaInfo.CaseHint, "", false, true, false, false, false);
        } else {
            GenerateOneLine(GCodeMetaInfo.DefaultHint + "default", false, false, false, true, null);
        }
    }

    @Override
    public boolean visit(final SynchronizedStatement node) {
        ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
        AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getBody(), node, false, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, node.getExpression(), "synchronized", GCodeMetaInfo.SynchronizedHint,
                        "", false, true, false, false, false);
            }
        });
        return super.visit(node);
    }

    @Override
    public boolean visit(ThrowStatement node) {
        ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
        return super.visit(node);
    }

    @Override
    public void endVisit(ThrowStatement node) {
        ExpressionReferPostHandle(node, node.getExpression(), "throw", GCodeMetaInfo.ThrowStatementHint, "", false,
                true, false, false, false);
        // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
        GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
    }

    @Override
    public boolean visit(CatchClause node) {
        runforbid.AddNodeHelp(node.getException().hashCode(), true);
        String nodecode = GCodeMetaInfo.CatchHint + "catch" + GCodeMetaInfo.WhiteSpaceReplacer
                + TypeUtil.TypeCode(node.getException().getType(), true);
        GenerateOneLine(nodecode, false, false, false, true, null);
        NewVariableDeclared(node.getException().getName(), node.getException().getType());
        return super.visit(node);
    }

    @Override
    public void endVisit(CatchClause node) {
        runforbid.DeleteNodeHelp(node.getException().hashCode());
        super.endVisit(node);
    }

    // below are judge and loop related.

    @Override
    public boolean visit(final WhileStatement node) {
        ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
        AddFirstOrderTask(new FirstOrderTask(node.getExpression(), null, node, true, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, node.getExpression(), "while", GCodeMetaInfo.WhileStatementHint, "",
                        false, true, false, false, false);
            }
        });
        return super.visit(node);
        /*
         * GenerateOneLine(GCodeMetaInfo.DescriptionHint + "while", false,
         * false, false, true, null);
         * referhint.AddNodeHelp(node.getExpression().hashCode(),
         * ReferenceHintLibrary.DataUse); AddFirstOrderTask(new
         * FirstOrderTask(node.getExpression(), null, node, true, false) {
         *
         * @Override public void run() {
         * GenerateOneLine(GCodeMetaInfo.DescriptionHint + "ecwhile", false,
         * false, false, true, null); } }); return super.visit(node);
         */
    }

    @Override
    public void endVisit(WhileStatement node) {
        referhint.DeleteNodeHelp(node.getExpression().hashCode());
    }

    @Override
    public boolean visit(final IfStatement node) {
        // GenerateOneLine(GCodeMetaInfo.IfStatementHint + "if", false, false,
        // false, true, null);
        // ExpressionReferPreHandle(node.getExpression(),
        // ReferenceHintLibrary.DataUse);

        // int exprhashcode = node.getExpression().hashCode();
        // referhint.AddNodeHelp(exprhashcode, ReferenceHintLibrary.DataUse);
        ExpressionReferPreHandle(node.getExpression(), ReferenceHintLibrary.DataUse);
        AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getThenStatement(), node, true, false) {
            @Override
            public void run() {
                ExpressionReferPostHandle(node, node.getExpression(), "if", GCodeMetaInfo.IfStatementHint, "", false,
                        true, false, false, false);
                // GenerateOneLine(GCodeMetaInfo.DescriptionHint + "then", false, false, false, true, null);
                // ExpressionReferPostHandle(node, (Expression)getPre(), "", "",
                // "", false, true, false, false, false);
            }
        });
        if (node.getElseStatement() != null) {
            AddFirstOrderTask(new FirstOrderTask(node.getThenStatement(), node.getElseStatement(), node, false, true) {
                @Override
                public void run() {
                    if (getPost() != null) {
                        GenerateOneLine(GCodeMetaInfo.DescriptionHint + "else", false, false, false, true, null);
                    }
                }
            });
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(IfStatement node) {
        referhint.DeleteNodeHelp(node.getExpression().hashCode());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(ForStatement node) {
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + "for", false, false, false, true, null);
        List<ASTNode> inis = node.initializers();
        boolean oneempty = false;
        boolean twoempty = false;
        boolean threeempty = false;
        if (inis == null || inis.size() == 0) {
            oneempty = true;
            GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FI", false, false, false, true, null);
        }
        Expression expr = node.getExpression();
        if (expr != null) {
            referhint.AddNodeHelp(expr.hashCode(), ReferenceHintLibrary.DataUse);
        }
        if (expr == null) {
            twoempty = true;
            if (oneempty) {
                GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FE", false, false, false, true, null);
            }
        }
        List<ASTNode> ups = node.updaters();
        if ((ups == null || ups.size() == 0)) {
            threeempty = true;
            if (oneempty && twoempty) {
                GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FU", false, false, false, true, null);
            }
        }
        ForBlockState fbs = new ForBlockState(oneempty, twoempty, threeempty);
        if (!oneempty) {
            AddFirstOrderTask(new FirstOrderTask(inis.get(inis.size() - 1), null, node, true, false, fbs) {
                @Override
                public void run() {
                    GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FI", false, false, false, true, null);
                    ForBlockState tfbs = (ForBlockState) getAdditionalinfo();
                    if (tfbs.isTwoempty()) {
                        GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FE", false, false, false, true, null);
                        if (tfbs.isThreeempty()) {
                            GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FU", false, false, false, true, null);
                        }
                    }
                }
            });
        }
        if (!twoempty) {
            AddFirstOrderTask(new FirstOrderTask(expr, null, node, true, false, fbs) {
                @Override
                public void run() {
                    GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FE", false, false, false, true, null);
                    ForBlockState tfbs = (ForBlockState) getAdditionalinfo();
                    if (tfbs.isThreeempty()) {
                        GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FU", false, false, false, true, null);
                    }
                }
            });
        }
        if (!threeempty) {
            AddFirstOrderTask(new FirstOrderTask(ups.get(ups.size() - 1), null, node, true, false) {
                @Override
                public void run() {
                    GenerateOneLine(GCodeMetaInfo.DescriptionHint + ";FU", false, false, false, true, null);
                }
            });
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(ForStatement node) {
        Expression expr = node.getExpression();
        if (expr != null) {
            referhint.DeleteNodeHelp(expr.hashCode());
        }
    }

    // below are VariableDeclarations

    @Override
    public boolean visit(VariableDeclarationExpression node) {
        // System.out.println("VariableDeclarationExpression:"+node);
        CDType cdt = GenCDType(node.getType());

        SetVeryRecentDeclaredType(cdt);
        // VariableDeclarationExpressionPreHandle(node.fragments(), node);
        return super.visit(node);
    }

    @Override
    public void endVisit(VariableDeclarationExpression node) {
        // VariableDeclarationExpressionPostHandle(node.fragments(), node);
        SetVeryRecentDeclaredType(null);
    }
	
	/*protected void VariableDeclarationExpressionPreHandle(List<VariableDeclarationFragment> fras, ASTNode node) {
		GenerateOneLine(GCodeMetaInfo.VDStart, false, false, false, true, null);
		Iterator<VariableDeclarationFragment> fitr = fras.iterator();
		while (fitr.hasNext()) {
			VariableDeclarationFragment vdf = fitr.next();
			AddFirstOrderTask(new FirstOrderTask(vdf, null, node, true, false, fitr.hasNext()) {
				@Override
				public void run() {
					if (!CheckLastIsSpecific(GCodeMetaInfo.VDStart) && !CheckLastIsSpecific(GCodeMetaInfo.VDSp)) {
						GenerateOneLine(GCodeMetaInfo.VDSp, false, false, false, true, null);
					}
				}
			});
			AddNodeRefered(vdf.hashCode(), null);
		}
	}

	protected void VariableDeclarationExpressionPostHandle(List<VariableDeclarationFragment> fras, ASTNode node) {
		String nodecode = GetVeryRecentDeclaredType();
		Iterator<VariableDeclarationFragment> fitr = fras.iterator();
		while (fitr.hasNext()) {
			VariableDeclarationFragment vdf = fitr.next();
			nodecode += referedcnt.GetNodeHelp(vdf.hashCode());
			if (fitr.hasNext()) {
				nodecode += ",";
			}
			DeleteNodeRefered(vdf.hashCode());
		}

		int nodehashcode = node.hashCode();
		if (CheckLastIsSpecific(GCodeMetaInfo.VDStart)) {
			RemoveLast();
		}
		if (NodeIsRefered(nodehashcode)) {
			referedcnt.AddNodeHelp(nodehashcode, nodecode);
		} else {
			GenerateOneLine(GCodeMetaInfo.VariableDeclarationHint + nodecode, false, false, false, true, null);
		}
	}*/
	
	/*protected boolean NodeIsForcedRefered(int nodehashcode) {
		Boolean bfr = beforcerefered.GetNodeHelp(nodehashcode) != null;
		if (bfr != null && bfr == true) {
			return true;
		}
		return false;
	}*/

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(SingleVariableDeclaration node) {
        // System.out.println("SingleVariableDeclaration:"+node);
        // MyLogger.Info("SingleVariableDeclaration:" + node);
        /*
         * Boolean forbid = runforbid.GetNodeHelp(node.hashCode()); if (forbid
         * != null && forbid == true) { return false; }
         */
        CDType cdt = GenCDType(node.getType());
        // String typecode = TypeCode(node.getType(), true);
        VariableDeclarationFragmentPreHandle(node.getInitializer(), node.getName(), cdt, node.extraDimensions(), true);
        return super.visit(node);
    }

    @Override
    public void endVisit(SingleVariableDeclaration node) {
        /*
         * int nodehashcode = node.hashCode(); int namehashcode =
         * node.getName().hashCode(); Boolean forbid =
         * runforbid.GetNodeHelp(nodehashcode); if (forbid != null && forbid ==
         * true) { int hint = ReferenceHintLibrary.DataDeclare; if
         * (VeryRecentIsFieldDeclared) { hint =
         * ReferenceHintLibrary.FieldDeclare; }
         * runpermit.AddNodeHelp(namehashcode, true);
         * referhint.AddNodeHelp(namehashcode, hint);
         * SetVeryRecentDeclaredType(node.getType().toString());
         * visit(node.getName()); runpermit.DeleteNodeHelp(namehashcode);
         * referhint.DeleteNodeHelp(namehashcode); return; }
         */
        VariableDeclarationFragmentPostHandle(node.getInitializer(), node.getName());
        // SetVeryRecentDeclaredType(null);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        return false;
    }

    @Override
    public void endVisit(FieldDeclaration node) {
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
        // System.out.println("VariableDeclarationStatement:"+node);
        // MyLogger.Error("VariableDeclarationStatement:"+node);
        // MyLogger.Error("VariableDeclarationStatementType:"+node.getType());
        CDType cdt = GenCDType(node.getType());

        SetVeryRecentDeclaredType(cdt);
        // VariableDeclarationExpressionPreHandle(node.fragments(), node);
        // String nodecode = GenerateVariableDeclarationTypeCode(typecode,
        // null);
        // GenerateOneLine(nodecode, false, false, false, true, null);
        return super.visit(node);
    }

    @Override
    public void endVisit(VariableDeclarationStatement node) {
        // AppendEndInfoToLast(GCodeMetaInfo.EndOfAStatement);
        // VariableDeclarationExpressionPostHandle(node.fragments(), node);
        GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
        SetVeryRecentDeclaredType(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(VariableDeclarationFragment node) {
        boolean fistnode = false;
        boolean operated = false;
        ASTNode np = node.getParent();
        if (np instanceof VariableDeclarationExpression) {
            List<VariableDeclarationFragment> fras = ((VariableDeclarationExpression) np).fragments();
            if (fras.get(0) == node) {
                fistnode = true;
            }
            operated = true;
        }
        if (np instanceof VariableDeclarationStatement) {
            List<VariableDeclarationFragment> fras = ((VariableDeclarationStatement) np).fragments();
            if (fras.get(0) == node) {
                fistnode = true;
            }
            operated = true;
        }
        if (np instanceof FieldDeclaration) {
            List<VariableDeclarationFragment> fras = ((FieldDeclaration) np).fragments();
            if (fras.get(0) == node) {
                fistnode = true;
            }
            operated = true;
        }
        if (!operated) {
            System.err.println("What the fuck, a kind of fragment parent not considered. THe type is:" + np.getClass() + ".");
            System.exit(1);
        }
        VariableDeclarationFragmentPreHandle(node.getInitializer(), node.getName(), null, node.extraDimensions(), fistnode);
        return super.visit(node);
    }

    @Override
    public void endVisit(VariableDeclarationFragment node) {
        VariableDeclarationFragmentPostHandle(node.getInitializer(), node.getName());
    }

    @Override
    public boolean visit(ConditionalExpression node) {
        Integer hint = referhint.GetNodeHelp(node.hashCode());
        referhint.AddNodeHelp(node.getExpression().hashCode(), ReferenceHintLibrary.DataUse);
        if (hint != null) {
            referhint.AddNodeHelp(node.getThenExpression().hashCode(), hint);
            referhint.AddNodeHelp(node.getElseExpression().hashCode(), hint);
        } else {
            referhint.AddNodeHelp(node.getThenExpression().hashCode(), ReferenceHintLibrary.DataUse);
            referhint.AddNodeHelp(node.getElseExpression().hashCode(), ReferenceHintLibrary.DataUse);
        }
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + "CondExpBegin", false, false, false, true, null);
        AddFirstOrderTask(new FirstOrderTask(node.getExpression(), node.getThenExpression(), node, false, false) {
            @Override
            public void run() {
                GenerateOneLine(GCodeMetaInfo.DescriptionHint + "CondExpQM", false, false, false, true, null);
            }
        });
        AddFirstOrderTask(new FirstOrderTask(node.getThenExpression(), node.getElseExpression(), node, false, false) {
            @Override
            public void run() {
                GenerateOneLine(GCodeMetaInfo.DescriptionHint + "CondExpCM", false, false, false, true, null);
            }
        });
        return super.visit(node);
    }

    @Override
    public void endVisit(ConditionalExpression node) {
        // GenerateOneLine(GCodeMetaInfo.DescriptionHint + "CondExpEnd", false,
        // false, false, true, null);
        referhint.DeleteNodeHelp(node.getExpression().hashCode());
        referhint.DeleteNodeHelp(node.getThenExpression().hashCode());
        referhint.DeleteNodeHelp(node.getElseExpression().hashCode());
    }

    // field access is highly related to the below.

    @Override
    public boolean visit(FieldAccess node) {
        // System.err.println("FieldAccess:"+node);
        // MyLogger.Info("FieldAccess:"+node);
        // MyLogger.Info("FieldAccessName:"+node.getName());
        // MyLogger.Info("FieldAccessExpr:"+node.getExpression());
        int nodehashcode = node.hashCode();
        Integer hint = referhint.GetNodeHelp(nodehashcode);
        ASTNode expr = node.getExpression();
        String exprcontent = expr.toString();
        int exprhashcode = expr.hashCode();
        int namehashcode = node.getName().hashCode();
        /*
         * if (expr instanceof FieldAccess || expr instanceof SuperFieldAccess)
         * { AddNodeRefered(exprhashcode); referhint.AddNodeHelp(exprhashcode,
         * hint); }
         */
        if (expr instanceof ThisExpression && exprcontent.equals("this")) {
            Integer changedhint = ReferenceHintLibrary.ChangeHintHighByteToField(hint);
            // referhint.AddNodeHelp(namehashcode, changedhint);
            AddNodeRefered(namehashcode, changedhint);
            runforbid.AddNodeHelp(exprhashcode, true);
        } else {
            runforbid.AddNodeHelp(namehashcode, true);
            // referhint.AddNodeHelp(exprhashcode, hint);
            AddNodeRefered(exprhashcode, hint);
        }
        return super.visit(node);
    }

    // field access should not be one line.
    // Must Ensure.
    @Override
    public void endVisit(FieldAccess node) {
        String nodecode = null;
        ASTNode expr = node.getExpression();
        String exprcontent = expr.toString();
        int exprhashcode = expr.hashCode();
        int namehashcode = node.getName().hashCode();
        if (expr instanceof ThisExpression && exprcontent.equals("this")) {
            nodecode = referedcnt.GetNodeHelp(namehashcode);
            referhint.DeleteNodeHelp(namehashcode);
            DeleteNodeRefered(namehashcode);
            runforbid.DeleteNodeHelp(exprhashcode);
        } else {
            String exprcode = referedcnt.GetNodeHelp(expr.hashCode());
            if (exprcode == null || exprcode.equals("")) {
                exprcode = GCodeMetaInfo.PreExist;
            }
            nodecode = node.getName().toString() + "." + exprcode;
            runforbid.DeleteNodeHelp(node.getName().hashCode());
            referhint.DeleteNodeHelp(exprhashcode);
            DeleteNodeRefered(exprhashcode);
        }

        // really generating codes.
        int nodehashcode = node.hashCode();
        if (NodeIsRefered(nodehashcode)) {
            referedcnt.AddNodeHelp(nodehashcode, nodecode);
            refernoline.AddNodeHelp(nodehashcode, true);
        } else {
            GenerateOneLine(nodecode, true, false, false, false, GCodeMetaInfo.FieldAccessHint);
        }
    }

    // below are most important method related : MethodDeclaration.

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(MethodDeclaration node) {
        // MyLogger.Info("MethodDeclarationParent:"+node.getParent().hashCode());
        mw.peek().Clear();
        if (omc != null) {
            FlushCode();
        }
        omc = new NodeCode(argmutiple);

        if (isFirstLevelASTNode(node) || ParentIsTypeDeclaration(node)) {
            ClearClassAndLabelInfo();
            ResetDLM();
        }
        String nodecode = "";
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        String rtcode = GCodeMetaInfo.ConstructionDeclarationHint;
        Type rt2 = node.getReturnType2();
        if (rt2 != null) {
            // is construction function.
            // System.err.print("MethodDeclaration:"+node);
            // System.exit(1);
            rtcode = TypeUtil.TypeCode(rt2, true);
            int ednum = node.getExtraDimensions();
            if (ednum > 0) {
                rtcode += StringUtil.GenerateDuplicates("[]", ednum);
            }
        }
        nodecode = nodecode + rtcode + "(";
        List<SingleVariableDeclaration> types = node.parameters();
        Iterator<SingleVariableDeclaration> itr = types.iterator();
        while (itr.hasNext()) {
            SingleVariableDeclaration t = itr.next();
            runforbid.AddNodeHelp(t.hashCode(), true);
            CDType typecode = GenCDType(t.getType());
            // String modifiedtypecode = typecode.getSimplifiedversion(); // especially for type '...'
            String sp = "";
            if (itr.hasNext()) {
                sp = ",";
            } else {
                if (t.isVarargs()) {
                    // modifiedtypecode = typecode + "[]"; // "#" +
                    typecode.appendSomething("#...");
                }
            }
            nodecode = nodecode + typecode.getSimplifiedversion() + sp;
            NewVariableDeclared(t.getName(), typecode);
        }
        /*
         * if (types.size() > 0) { nodecode = nodecode.substring(0,
         * nodecode.length() - 1); }
         */
        nodecode = nodecode + ")" + node.getName().toString();
		/*if (acp.IsInAnonymous())
		{
			mw.peek().PushMethodName(node.getName().toString());
		} else {*/
        GenerateOneLine(GCodeMetaInfo.MethodDeclarationHint + nodecode, false, false, false, true, null);
        //}
        return super.visit(node);
    }

    private boolean ParentIsTypeDeclaration(BodyDeclaration node) {
        if (node.getParent() instanceof TypeDeclaration) {
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(MethodDeclaration node) {
        List<SingleVariableDeclaration> types = node.parameters();
        Iterator<SingleVariableDeclaration> itr = types.iterator();
        while (itr.hasNext()) {
            SingleVariableDeclaration t = itr.next();
            runforbid.DeleteNodeHelp(t.hashCode());
        }
        //if (isFirstLevelASTNode(node)) {
        //} else {
        //	OneSentenceEnd();
        //}
        runforbid.DeleteNodeHelp(node.getName().hashCode());
        FlushCode();
    }

    // below are all method invocations.

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(final ClassInstanceCreation node) {
        // MyLogger.Info("Node Type:"+node.getType());
        // MyLogger.Info("Body:"+node.getAnonymousClassDeclaration());
        CDType cdt = GenCDType(node.getType());
        OneMethodInvocationOccurs(cdt.getSimplifiedversion());
        MethodPushReferRequest(node.getExpression(), node.arguments());
        if (node.getAnonymousClassDeclaration() != null) {
            AddFirstOrderTask(new FirstOrderTask(null, node.getAnonymousClassDeclaration(), node, false, true) {
                @Override
                public void run() {
                    ClassInstanceCreationDetail(node);
                    GenerateOneLine(GCodeMetaInfo.DescriptionHint + "AnonymousDeclaration", false, false, false, true,
                            null);
                }
            });
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(ClassInstanceCreation node) {
        // MyLogger.Info("Node Type:"+node.getType());
        // MyLogger.Info("Body:"+node.getAnonymousClassDeclaration());
        if (node.getAnonymousClassDeclaration() == null) {
            ClassInstanceCreationDetail(node);
        }
        super.endVisit(node);
    }

    @SuppressWarnings("unchecked")
    protected void ClassInstanceCreationDetail(ClassInstanceCreation node) {
        Expression expr = node.getExpression();
        String invoker = "new";
        if (expr != null) {
            int exprhashcode = expr.hashCode();
            String refercnt = referedcnt.GetNodeHelp(exprhashcode);
            if (refercnt != null) {
                invoker += ("." + refercnt);
            }
        }
        MethodInvocationCode(TypeUtil.TypeCode(node.getType(), true), invoker, node.arguments());
        MethodDeleteReferRequest(expr, node.arguments());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(ConstructorInvocation node) {
        MethodPushReferRequest(null, node.arguments());
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(ConstructorInvocation node) {
        // Do nothing now.
        // MyLogger.Info("ConstructorInvocation:" + node);
        MethodInvocationCode("this", "this", node.arguments());
        MethodDeleteReferRequest(null, node.arguments());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(SuperConstructorInvocation node) {
        MethodPushReferRequest(node.getExpression(), node.arguments());
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(SuperConstructorInvocation node) {
        Expression expr = node.getExpression();
        String invoker = "this";
        if (expr != null) {
            int exprhashcode = expr.hashCode();
            String refercnt = referedcnt.GetNodeHelp(exprhashcode);
            if (refercnt != null) {
                invoker = refercnt;
            } else {
                invoker = GCodeMetaInfo.PreExist;
            }
        }
        MethodInvocationCode("super", invoker, node.arguments());
        MethodDeleteReferRequest(expr, node.arguments());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(SuperMethodInvocation node) {
        OneMethodInvocationOccurs(node.getName().toString());
        MethodPushReferRequest(null, node.arguments());
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        if (node.getQualifier() != null) {
            // runforbid.AddNodeHelp(node.getQualifier().hashCode(), true);
            AddNodeRefered(node.getQualifier().hashCode(), ReferenceHintLibrary.DataUse);
        }
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(SuperMethodInvocation node) {
        String invoker = "super";
        if (node.getQualifier() != null) {
            String qualifiercnt = referedcnt.GetNodeHelp(node.getQualifier().hashCode());
            // GetDefaultStrictedLengthOfName(node.getQualifier())
            if (qualifiercnt == null || qualifiercnt.equals("")) {
                qualifiercnt = GCodeMetaInfo.PreExist;
            }
            invoker += "." + qualifiercnt;
            DeleteNodeRefered(node.getQualifier().hashCode());
        }
        MethodInvocationCode(node.getName().toString(), invoker, node.arguments());
        MethodDeleteReferRequest(null, node.arguments());

        runforbid.DeleteNodeHelp(node.getName().hashCode());
        /*
         * if (node.getQualifier() != null) {
         * runforbid.DeleteNodeHelp(node.getQualifier().hashCode()); }
         */
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(MethodInvocation node) {

        // System.err.println("mi:" + node);

        OneMethodInvocationOccurs(node.getName().toString());
        MethodPushReferRequest(node.getExpression(), node.arguments());
        runforbid.AddNodeHelp(node.getName().hashCode(), true);
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(MethodInvocation node) {
        // MyLogger.Info("MethodInvocation:" + node);
        Expression expr = node.getExpression();
        String invoker = "this";
        if (expr != null) {
            int exprhashcode = expr.hashCode();
            String refercnt = referedcnt.GetNodeHelp(exprhashcode);
            if (refercnt != null) {
                invoker = refercnt;
            } else {
                invoker = GCodeMetaInfo.PreExist;
            }
        }
        MethodInvocationCode(node.getName().toString(), invoker, node.arguments());
        MethodDeleteReferRequest(expr, node.arguments());

        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    // raw names : handle types : SuperFieldAccess or Name

    @Override
    public boolean visit(SuperFieldAccess node) {
        // MyLogger.Info("SuperFieldAccess:" + node);
        // MyLogger.Info("SuperFieldAccess Qualifier:" +
        // MyLogger.Info("SuperFieldAccess Name:" + node.getName());
        return QualifiedPreHandle(node, node.getQualifier(), node.getName()) && super.visit(node);
    }

    // this should never be a line.
    @Override
    public void endVisit(SuperFieldAccess node) {
        Name qualifier = node.getQualifier();
        Name name = node.getName();
        QualifiedPostHandle(node, qualifier, name.toString(), name.hashCode(), "super", ".", true);
        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    @Override
    public boolean visit(QualifiedName node) {
        return QualifiedPreHandle(node, node.getQualifier(), node.getName()) && super.visit(node);
    }

    @Override
    public void endVisit(QualifiedName node) {
        /*
         * int nodehashcode = node.hashCode(); Boolean forbid =
         * runforbid.GetNodeHelp(nodehashcode); if (forbid != null && forbid ==
         * true) { return; }
         */
        Name qualifier = node.getQualifier();
        Name name = node.getName();
        QualifiedPostHandle(node, qualifier, name.toString(), name.hashCode(), null, null, true);
        /*
         * int len = PredictLength(node); if (len <= StrictedNameLength) { Name
         * qualifier = ; Name name = ; QualifiedPostHandle(node, qualifier,
         * name, null, null);
         * runforbid.DeleteNodeHelp(node.getName().hashCode()); }
         */
    }

    /*
     * protected int PredictLength(Name node) { if (node instanceof SimpleName)
     * { return 1; } else { return PredictLength(((QualifiedName)
     * node).getQualifier()) + 1; } }
     *
     * protected String GetDefaultStrictedLengthOfName(Name node) { int len =
     * PredictLength(node); if (len == 1) { return node.toString(); } return
     * GetStrictedLengthOfName((QualifiedName) node,
     * Math.min(StrictedNameLength, len)); }
     */

    protected String GetStrictedLengthOfName(QualifiedName node, int len) {
        String cnt = "";
        SimpleName name = null;
        while (len > 0) {
            len--;
            String cat = ".";
            if (len == 0) {
                cat = "";
            }
            if (len >= 1) {
                cnt += node.getName().toString() + cat;
            } else {
                cnt += name.toString() + cat;
            }
            if (len > 1) {
                node = (QualifiedName) node.getQualifier();
            }
            if (len == 1) {
                Name qualifier = node.getQualifier();
                if (qualifier instanceof QualifiedName) {
                    name = ((QualifiedName) qualifier).getName();
                } else {
                    name = (SimpleName) qualifier;
                }
            }
        }
        return cnt;
    }

    protected boolean QualifiedPreHandle(ASTNode node, Name qualifier, SimpleName sname) {
        /*
         * int nodehashcode = node.hashCode(); Boolean forbid =
         * runforbid.GetNodeHelp(nodehashcode); if (forbid != null && forbid ==
         * true) { return false; }
         */
        if (qualifier != null) {
            int qualihashcode = qualifier.hashCode();
            AddNodeRefered(qualihashcode, referhint.GetNodeHelp(node.hashCode()));
            // runforbid.AddNodeHelp(, true);
        }
        if (sname != null) {
            runforbid.AddNodeHelp(sname.hashCode(), true);
        }
        return true;
    }

    protected void QualifiedPostHandle(ASTNode node, Name qualifier, String namestr, Integer namehashcode,
                                       String additional, String additionalprefixoperator, boolean mustberefered) {

        // MyLogger.Info("node:" + node);
        // MyLogger.Info("qualifier:" + qualifier);
        // MyLogger.Info("node is refered:" + NodeIsRefered(node.hashCode()));

        int nodehashcode = node.hashCode();
        /*
         * Boolean forbid = runforbid.GetNodeHelp(nodehashcode); if (forbid !=
         * null && forbid == true) { return; }
         */
        String qualicnt = "";
        if (qualifier != null) {
            String ref = referedcnt.GetNodeHelp(qualifier.hashCode());
            if (ref == null || ref.equals("")) {
                ref = GCodeMetaInfo.PreExist;
            }
            qualicnt = "." + ref;
        }
        String nodecode = namestr + (additional != null ? additionalprefixoperator + additional : "") + qualicnt;
        // GetDefaultStrictedLengthOfName(qualifier)
        if (NodeIsRefered(nodehashcode)) {
            referedcnt.AddNodeHelp(nodehashcode, nodecode);
            refernoline.AddNodeHelp(nodehashcode, true);
        } else {
            // if (mustberefered)
            // {
            // System.err.println("this must be refered, but not. Node:" + node
            // + ";NodeParent:" + node.getParent());
            // System.exit(1);
            // }
            GenerateOneLine(nodecode, true, false, false, false, GCodeMetaInfo.QualifiedHint);
        }
        if (qualifier != null) {
            int qualihashcode = qualifier.hashCode();
            DeleteNodeRefered(qualihashcode);
            referhint.DeleteNodeHelp(qualihashcode);
        }
        if (namehashcode != null) {
            runforbid.DeleteNodeHelp(namehashcode);
        }
    }

    @Override
    public boolean visit(SimpleName node) {

        int nodehashcode = node.hashCode();
        Integer hint = referhint.GetNodeHelp(node.hashCode());

        boolean isfield = false;
        String result = null;

        if (hint == null) {
            System.err.println("hint null: SimpleName:" + node + ";SimpleNameParent:" + node.getParent()
                    + ";SimpleNameParentType:" + node.getParent().getClass());
        }

        if (hint != ReferenceHintLibrary.NoHint) {
            String code = null;
            boolean hasCorrespond = false;
            String data = node.toString();
            switch (hint) {
                case ReferenceHintLibrary.DataUse:
                    /*
                     * if (data.equals("a")) { MyLogger.Info("DataUse"); }
                     */
                    // MyLogger.Info("datause:"+data);
                    code = GetDataOffset(data, false, false);
                    break;
                case ReferenceHintLibrary.FieldUse:
                    /*
                     * if (data.equals("a")) { MyLogger.Info("FieldUse"); }
                     */
                    isfield = true;
                    code = GetDataOffset(data, true, false);
                    break;
                case ReferenceHintLibrary.DataUpdate:
                    /*
                     * if (data.equals("a")) { MyLogger.Info("DataUpdate"); }
                     */
                    code = GetDataOffset(data, false, false);
                    // DataNewlyUsed(data, null, false, false, false, false, false);
                    break;
                case ReferenceHintLibrary.FieldUpdate:
                    /*
                     * if (data.equals("a")) { MyLogger.Info("FieldUpdate"); }
                     */
                    isfield = true;
                    code = GetDataOffset(data, true, false);
                    // DataNewlyUsed(data, null, false, false, false, true, false);
                    break;
                case ReferenceHintLibrary.DataDeclare:
                    /*
                     * if (data.equals("a")) { MyLogger.Info("DataDeclare"); }
                     */
                    CDType declaredtype = GetVeryRecentDeclaredType();
                    // MyLogger.Info("common declaredtype:" + declaredtype
                    // + "; and data is:" + data + "; and is final:" +
                    // GetVeryRecentDeclaredFinal());
                    CheckVeryRecentDeclaredTypeMustNotNull(declaredtype);
                    // MyLogger.Error("data is:" + data + "
                    // declaredtype:"+declaredtype);
                    if (RunMetaInfo.DebugMode) {
                        System.out.println("complex type:" + declaredtype.getComplexversion());
                    }
                    DataNewlyUsed(data, declaredtype.getComplexversion(), false, false, true, false, false);
                    hasCorrespond = true;
                    break;
                case ReferenceHintLibrary.FieldDeclare:
                    /*
                     * if (data.equals("a")) { MyLogger.Info("FieldDeclare"); }
                     */
                    isfield = true;
                    CDType declaredtype2 = GetVeryRecentDeclaredType();
                    CheckVeryRecentDeclaredTypeMustNotNull(declaredtype2);
                    if (RunMetaInfo.DebugMode) {
                        System.out.println("complex type:" + declaredtype2.getComplexversion());
                    }
                    DataNewlyUsed(data, declaredtype2.getComplexversion(), false, true, false, false, false);
                    hasCorrespond = true;
                    break;
                default:
                    break;
            }
            if (code != null) {
                result = code;
            } else {
                if (!hasCorrespond) {
                    String nodestr = node.toString();
                    String pre = (isfield ? "this." : "");
                    if (Character.isLowerCase(nodestr.charAt(0)) == true) {
                        MyLogger.Error("Debugging Data: " + node
                                + "; No corresponding data offset. Maybe data use or others.");
                    }
                    result = pre + nodestr;
                }
            }
        } else {
            String nodestr = node.toString();
            if (Character.isLowerCase(nodestr.charAt(0)) == true) {
                MyLogger.Error("Warning Data: " + node
                        + "; just for debugging and testing. The simple name does not have hint.");
            }
            result = nodestr;
        }

        if (result == null) {
            if (hint == ReferenceHintLibrary.DataDeclare || hint == ReferenceHintLibrary.FieldDeclare) {
                // do nothing.
            } else {
                MyLogger.Error("What the fuck. How serious the error is!");
                new Exception().printStackTrace();
                System.exit(1);
            }
        } else {
            if (NodeIsRefered(nodehashcode)) {
                referedcnt.AddNodeHelp(nodehashcode, result);
                refernoline.AddNodeHelp(nodehashcode, true);
            } else {
                GenerateOneLine(result, false, false, false, false, GCodeMetaInfo.NameHint);
            }
        }
        return super.visit(node);
    }

    // the handle of the following types should use the helper function

    protected CDType GenCDType(Type node) {
        return new CDType(TypeUtil.TypeCode(node, true), TypeUtil.TypeCode(node, false));
    }

    /*
     * protected String GetStrictedName(Name name, int alreadylen) { String
     * result = null; if (name != null && alreadylen > 0) { if (name instanceof
     * QualifiedName) { QualifiedName qn = (QualifiedName) name; result =
     * qn.getName().toString(); String qs = GetStrictedName(qn, alreadylen - 1);
     * if (qs != null) { result += "." + qs; } } if (name instanceof SimpleName)
     * { result = name.toString(); } } return result; }
     */

    @Override
    public boolean visit(IntersectionType node) {
        // type & type
        // MyLogger.Info("IntersectionType:" + node);
        // do nothing.
        return super.visit(node);
    }

    @Override
    public boolean visit(UnionType node) {
        // type | type
        // MyLogger.Info("UnionType:" + node);
        // do nothing.
        return super.visit(node);
    }

    @Override
    public boolean visit(PrimitiveType node) {
        // MyLogger.Info("PrimitiveType:" + node);
        // do nothing. but this is different.
        return false;
    }

    @Override
    public boolean visit(WildcardType node) {
        // MyLogger.Info("WildcardType:" + node);
        // do nothing.
        return false;
    }

    ;

    @Override
    public boolean visit(SimpleType node) {
        // MyLogger.Info("SimpleType:" + node);
        // do nothing.
        return false;
    }

    @Override
    public boolean visit(QualifiedType node) {
        // MyLogger.Info("QualifiedType:"+node);
        // do nothing.
        return false;
    }

    @Override
    public boolean visit(ArrayType node) {
        // MyLogger.Info("ArrayType:"+node);
        // do nothing.
        return false;
    }

    @Override
    public boolean visit(ParameterizedType node) {
        // MyLogger.Info("ParameterizedType:"+node);
        // do nothing.
        return false;
    }

    @Override
    public boolean visit(NameQualifiedType node) {
        // MyLogger.Info("NameQualifiedType:"+node);
        // do nothing.
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean visit(final EnumConstantDeclaration node) {
        AppendOtherCode(GCodeMetaInfo.EnumCorpus, node.getName().toString());
        MethodPushReferRequest(null, node.arguments());
        runforbid.AddNodeHelp(node.getName().hashCode(), true);

        /*
         * ASTNode over = node.getName(); if (node.arguments() != null &&
         * node.arguments().size() > 0) { over = (ASTNode)
         * node.arguments().get(node.arguments().size()-1); }
         * System.err.println("over hash code:" + over.hashCode());
         * System.err.println("over content:" + over);
         */

        if (node.getAnonymousClassDeclaration() != null) {
            AddFirstOrderTask(new FirstOrderTask(null, node.getAnonymousClassDeclaration(), node, false, true) {
                @Override
                public void run() {
                    String invoker = "this";
                    EnumConstantInvocationCode(node.getName().toString(), invoker, node.arguments());
                    MethodDeleteReferRequest(null, node.arguments());
                    GenerateOneLine(GCodeMetaInfo.DescriptionHint + "AnonymousDeclaration", false, false, false, true,
                            null);
                }
            });
        }
        return super.visit(node);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endVisit(EnumConstantDeclaration node) {
        if (node.getAnonymousClassDeclaration() == null) {
            String invoker = "this";
            EnumConstantInvocationCode(node.getName().toString(), invoker, node.arguments());
            MethodDeleteReferRequest(null, node.arguments());
        }
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EnumConstantDeclarationSplitComma, false, false,
                false, true, null);
        runforbid.DeleteNodeHelp(node.getName().hashCode());
    }

    @Override
    public boolean visit(TypeLiteral node) {
        Type type = node.getType();
        String typecode = "void";
        if (type != null) {
            typecode = TypeUtil.TypeCode(type, true);
        }
        String nodecode = "class." + typecode;
        RawLiteralHandle(node, nodecode);
        return super.visit(node);
    }

    @Override
    public boolean visit(NullLiteral node) {
        RawLiteralHandle(node, null);
        return false;
    }

    @Override
    public boolean visit(BooleanLiteral node) {
        RawLiteralHandle(node, null);
        return false;
    }

    @Override
    public boolean visit(StringLiteral node) {
        // MyLogger.Info("StringLiteral:"+node);
        String literal = node.toString().trim();
        AppendOtherCode(GCodeMetaInfo.StringCorpus, literal.substring(1, literal.length() - 1));
        RawLiteralHandle(node, GCodeMetaInfo.StringHolder);
        return false;
    }

    @Override
    public boolean visit(NumberLiteral node) {
        // MyLogger.Info("NumberLiteral:"+node);
        AppendOtherCode(GCodeMetaInfo.NumberCorpus, node.toString());

        RawLiteralHandle(node, null);
        return false;
    }

    @Override
    public boolean visit(CharacterLiteral node) {
        // MyLogger.Info("CharacterLiteral:"+node);
        AppendOtherCode(GCodeMetaInfo.StringCorpus, node.charValue() + "");
        AppendOtherCode(GCodeMetaInfo.CharCorpus, node.charValue() + "");

        RawLiteralHandle(node, null);
        return false;
    }

    // helper functions

    protected void RawLiteralHandle(ASTNode node, String nodecode) {
        if (nodecode == null) {
            nodecode = node.toString();
        }
        // special for char.
        if (nodecode.trim().equals("' '") || nodecode.trim().equals("'	'")) {
            // System.out.println("Exist ' ', stop to watch closely.");
            // System.exit(1);
            nodecode = GCodeMetaInfo.WhiteSpace;
        }
        // especially for some error situation.
        if (nodecode.startsWith("'\\uu")) {
            nodecode = "'\\u" + nodecode.substring("'\\uu".length(), nodecode.length());
        }
        int nodehashcode = node.hashCode();
        if (NodeIsRefered(nodehashcode)) {
            referedcnt.AddNodeHelp(nodehashcode, nodecode);
            try {
                refernoline.AddNodeHelp(nodehashcode, true);
            } catch (Error e) {
                System.err.println("Conflict node content:" + node.toString());
                System.exit(1);
            }
        } else {
            GenerateOneLine(nodecode, false, false, false, false, GCodeMetaInfo.LiteralHint);
        }
    }

    /*
     * protected void AnonymousClassDeclarationCodeFileAddMethodWindow() {
     * ((OneJavaFileAnonymousClassesCode)jc).AddPreDeclrations(mw); }
     */

    protected void OneMethodInvocationOccurs(String rawmethodname) {
        // System.out.println("OneMethodInvocationOccurs:" + rawmethodname);
        mw.peek().PushMethodName(rawmethodname);
    }

    private boolean isFirstLevelASTNode(ASTNode node) {
        int parenthashcode = node.getParent().hashCode();
        if (parenthashcode == FirstLevelClass) {
            return true;
        }
        return false;
    }

    protected void PushMethodNodeCodeToJavaFileCode() {
        jc.AddOneMethodNodeCode(omc);
    }

    protected void OneSentenceEnd() {
        jc.OneSentenceEnd();
    }

    protected void FlushCode() {
        if (omc != null && !omc.IsEmpty()) {
            PushMethodNodeCodeToJavaFileCode();
            OneSentenceEnd();
        }
        omc = null;
        if (omc == null) {
            omc = (new NodeCode(argmutiple));
        }
    }

    protected void MoveSpecificLineUntilLastToBeforeSpecificLine(int exprstartline, int dowhileline) {
        omc.MoveSpecificLineUntilLastToBeforeSpecificLine(exprstartline, dowhileline);
    }

    protected void MoveLastToSpecificLine(int line) {
        omc.MoveLastToSpecificLine(line);
    }

    protected void AppendOtherCode(String corpus, String code) {
        ocm.AppendOtherCode(corpus, code);
    }

    protected void AddFirstOrderTask(FirstOrderTask runtask) {
        fotp.InfixNodeAddFirstOrderTask(runtask);
    }

    protected void CheckEnterMethodParam() {
        omc.CheckEnterMethodParam();
    }

    protected void GenerateOneLine(String nodecode, boolean couldappend, boolean mustappend, boolean mustpre,
                                   boolean occupyoneline, String preHint) {
        omc.AddOneLineCode(nodecode, couldappend, mustappend, mustpre, occupyoneline, preHint);
    }

    protected void RemoveLast() {
        omc.RemoveLast();
    }

    protected boolean CheckLastIsSpecific(String checkcode) {
        return omc.CheckLastIsSpecific(checkcode);
    }

    protected int RecordCurrentLastIndex() {
        return omc.RecordCurrentLastIndex();
    }

    protected void GenerateEndInfo(String lcode) {
        omc.GenerateEndInfo(lcode);
    }

    protected void AppendEndInfoToLast(String ncode) {
        omc.AppendEndInfoToLast(ncode);
    }

    // If doesn't know the kind, just set one as random. The one must be the big
    // kind you want.
    protected String GetDataOffset(String data, boolean isFieldUseOrUpdate, boolean isCommonUseOrUpdate) {
        String code = sdm.GetDataOffsetInfo(data, isFieldUseOrUpdate, isCommonUseOrUpdate);
        return code;
    }

    protected void ClassNewlyAssigned(String type) {
        cjcs.PushNewlyAssignedData(type, GCodeMetaInfo.HackedNoType);
    }

    protected String GetClassOffset(String type) {
        Integer offset = cjcs.GetExactOffset(type, GCodeMetaInfo.HackedNoType);
        if (offset == null) {
            return null;
        }
        return "@" + "K" + 0 + GCodeMetaInfo.OffsetSpiliter + OffsetLibrary.GetOffsetDescription(offset);
    }

    protected void LabelNewlyAssigned(String label) {
        ljcs.PushNewlyAssignedData(label, GCodeMetaInfo.HackedNoType);
    }

    protected String GetLabelOffset(String label) {
        Integer offset = ljcs.GetExactOffset(label, GCodeMetaInfo.HackedNoType);
        if (offset == null) {
            return null;
        }
        return "@" + "L" + 0 + GCodeMetaInfo.OffsetSpiliter + OffsetLibrary.GetOffsetDescription(offset);
    }

    protected void DataNewlyUsed(String data, String type, boolean isfianl, boolean isFieldDeclare,
                                 boolean isCommonDeclare, boolean isFieldUseOrDeclare, boolean isCommonUseOrDeclare) {
        sdm.AddDataNewlyUsed(data, type, isfianl, isFieldDeclare, isCommonDeclare, isFieldUseOrDeclare,
                isCommonUseOrDeclare);
    }

    protected CDType GetVeryRecentDeclaredType() {
        if (VeryRecentDeclaredType.size() > 0) {
            return VeryRecentDeclaredType.peek();
        }
        return null;
    }

    protected void CheckVeryRecentDeclaredTypeMustNotNull(CDType declaredtype) {
        if (declaredtype == null || declaredtype.getSimplifiedversion() == null) {
            MyLogger.Error("No Declared Type? The system will exit.");
            new Exception("No Recent Declared Type").printStackTrace();
            System.exit(1);
        }
    }

    protected void ClearClassAndLabelInfo() {
        cjcs.ClearAll();
        ljcs.ClearAll();
    }

    protected boolean NodeIsRefered(int nodehashcode) {
        Boolean isrefered = berefered.GetNodeHelp(nodehashcode);
        if (isrefered != null && isrefered == true) {
            return true;
        }
        return false;
    }

    /*
     * protected boolean NodeIsAlreadyRefered(int nodehashcode) { Boolean
     * isrefered = bereferedAlready.GetNodeHelp(nodehashcode); if (isrefered !=
     * null && isrefered == true) { return true; } return false; }
     */

    protected void ExpressionReferPreHandle(Expression expr, int referenceHint) {
        int exprhashcode = expr.hashCode();
        // referhint.AddNodeHelp(exprhashcode, referenceHint);
        AddNodeRefered(exprhashcode, referenceHint);
    }

    protected String ExpressionReferPostHandle(ASTNode node, Expression expr, String operator, String operatorHint,
                                               String addedcnt, boolean exprisleft, boolean needaddsplitter, boolean couldAppend, boolean mustAppend,
                                               boolean asreturn) {
        boolean mustOneLine = false;
        int exprhashcode = expr.hashCode();
        String exprcode = referedcnt.GetNodeHelp(exprhashcode);
        Boolean exprnoline = refernoline.GetNodeHelp(exprhashcode);
        if (exprnoline == null || exprnoline == false) {
            exprnoline = false;
        }
        boolean occupyline = true;
        if (exprnoline) {
            occupyline = false;
        }
        if (operator != null && !operator.equals("")) {
            mustOneLine = true;
        }
        if (addedcnt != null && !addedcnt.equals("")) {
            mustOneLine = true;
        }
        if (mustOneLine) {
            occupyline = true;
        }
        /*
         * if (node instanceof PrefixExpression || node instanceof
         * PostfixExpression) { occupyline = false; }
         */
        /*
         * if (node instanceof PrefixExpression) { MyLogger.Info("nodestr:" +
         * node + ";node expr null?" + (exprcode == null)); }
         */
        boolean exprused = false;
        if (exprcode == null) {
            if ((operator == null || operator.equals("")) && (addedcnt == null || addedcnt.equals(""))) {
                exprcode = "";
            } else {
                exprcode = GCodeMetaInfo.PreExist;
            }
        } else {
            if (!needaddsplitter) {
                if (CheckAppend() && exprnoline && !occupyline && exprisleft
                        && !(node instanceof PostfixExpression || node instanceof PrefixExpression)) {
                    GenerateOneLine(exprcode, false, false, false, false, null);
                    exprused = true;
                    exprcode = "";
                }
            }
        }

        // MyLogger.Info("nodestr:"+node+";expr:"+expr+";exprcode:"+exprcode+";operator:"+operator);

        String nodecode = "";
        String splitter = (needaddsplitter ? GCodeMetaInfo.CommonSplitter : "");
        if (exprisleft) {
            operator = (operator == null || operator.equals("")) ? "" : (splitter + operator);
            addedcnt = (addedcnt == null || addedcnt.equals("")) ? "" : (splitter + addedcnt);
        } else {
            operator = (operator == null || operator.equals("")) ? "" : (operator + splitter);
            addedcnt = (addedcnt == null || addedcnt.equals("")) ? "" : (addedcnt + splitter);
        }
        if (exprisleft) {
            nodecode = exprcode + operator + addedcnt;
        } else {
            nodecode = addedcnt + operator + exprcode;
        }
        if (!asreturn && !nodecode.equals("")) {
            int nodehashcode = node.hashCode();
            if (!mustOneLine && NodeIsRefered(nodehashcode)) {
                referedcnt.AddNodeHelp(nodehashcode, nodecode);
                refernoline.AddNodeHelp(nodehashcode, true);
            } else {
                if (!occupyline) {
                    operatorHint = "";
                }
                boolean mustPre = false;
                if (exprused && exprisleft) {
                    mustPre = true;
                }
                GenerateOneLine(nodecode, couldAppend, mustAppend, mustPre, occupyline, operatorHint);
            }
        }

        referhint.DeleteNodeHelp(exprhashcode);
        DeleteNodeRefered(exprhashcode);
        if (nodecode.equals("")) {
            // new Exception("====== How Strange ======").printStackTrace();
            // return "============================================== How
            // Strange ==============================================";
            nodecode = null;
        }
        return nodecode;
    }

    /*
     * protected void SetVeryRecentNotGenerateCode(boolean
     * veryRecentNotGenerateCode) { VeryRecentNotGenerateCode =
     * veryRecentNotGenerateCode; }
     */

    protected void SetVeryRecentDeclaredType(CDType veryRecentDeclaredType) {
        if (veryRecentDeclaredType == null) {
            VeryRecentDeclaredType.pop();
        } else {
            VeryRecentDeclaredType.push(veryRecentDeclaredType);
        }
    }

    protected CDType GenerateVariableDeclarationType(CDType typecode, List<Dimension> dimens) {
        String dimenstr = "";
        if (dimens != null && dimens.size() > 0) {
            Iterator<Dimension> itr = dimens.iterator();
            while (itr.hasNext()) {
                itr.next();
                dimenstr += "[]";
            }
        }
        return new CDType(typecode.getSimplifiedversion() + dimenstr, typecode.getComplexversion() + dimenstr);
    }

    protected boolean CheckAppend() {
        return omc.CheckAppend();
    }

    protected void CheckHint(Integer hint) {
        if (hint == null || hint == ReferenceHintLibrary.NoHint) {
            MyLogger.Error("There is no hint in this node handle. The system will exit.");
            new Exception("No hint exception").printStackTrace();
            System.exit(1);
        }
    }

    protected void CheckCode(String code) {
        if (code == null) {
            MyLogger.Error("There is no code in this node handle. The system will exit.");
            new Exception("No code exception").printStackTrace();
            System.exit(1);
        }
    }

    protected void AddNodeRefered(int nodehashcode, Integer hint) {
        berefered.AddNodeHelp(nodehashcode, true);
        if (hint != null) {
            referhint.AddNodeHelp(nodehashcode, hint);
        }
    }

	/*protected void AddNodeForcedRefered(int nodehashcode, Integer hint) {
		beforcerefered.AddNodeHelp(nodehashcode, true);
		berefered.AddNodeHelp(nodehashcode, true);
		if (hint != null) {
			referhint.AddNodeHelp(nodehashcode, hint);
		}
	}*/

    protected void DeleteNodeRefered(int nodehashcode) {
        // if (NodeIsAlreadyRefered(nodehashcode)) {
        // bereferedAlready.DeleteNodeHelp(nodehashcode);
        // } else {
        berefered.DeleteNodeHelp(nodehashcode);
        referedcnt.DeleteNodeHelp(nodehashcode);
        refernoline.DeleteNodeHelp(nodehashcode);
        referhint.DeleteNodeHelp(nodehashcode);
        // }
    }

	/*protected void DeleteNodeForcedRefered(int nodehashcode) {
		// if (NodeIsAlreadyRefered(nodehashcode)) {
		// bereferedAlready.DeleteNodeHelp(nodehashcode);
		// } else {
		beforcerefered.DeleteNodeHelp(nodehashcode);
		berefered.DeleteNodeHelp(nodehashcode);
		referedcnt.DeleteNodeHelp(nodehashcode);
		refernoline.DeleteNodeHelp(nodehashcode);
		referhint.DeleteNodeHelp(nodehashcode);
		// }
	}*/

    public Map<String, ContentsAndWords> GetGeneratedCode() {
        Map<String, ContentsAndWords> result = new TreeMap<String, ContentsAndWords>();
        result.putAll(ocm.getOtherCodeMap());
        int totalwords = 0;
        StringBuilder sb = new StringBuilder("");
        if (!ojfc.IsEmpty()) {
            sb.append(ojfc.toString());
            totalwords += ojfc.getAllWords();
        }
        if (!acp.IsEmpty()) {
            sb.append(acp.toString());
            totalwords += acp.getAllWords();
        }
        if (sb.length() > 0) {
            result.put(GCodeMetaInfo.LogicCorpus, new ContentsAndWords(sb.toString(), totalwords));
        }
        return result;
    }

    protected void TypePreHandle(Type type, boolean simplified) {
        berefered.AddNodeHelp(type.hashCode(), true);
        if (simplified) {
            typesimp.AddNodeHelp(type.hashCode(), true);
        }
    }

    protected String TypeContent(Type type) {
        return referedcnt.GetNodeHelp(type.hashCode());
    }

    protected void TypePostHandle(Type type) {
        int typehashcode = type.hashCode();
        berefered.DeleteNodeHelp(typehashcode);
        referedcnt.DeleteNodeHelp(typehashcode);
        refernoline.DeleteNodeHelp(typehashcode);
        typesimp.DeleteNodeHelp(typehashcode);
    }

    protected void EnterBlock(ASTNode node) {
        // MyLogger.Info("Hashcode:"+node.hashCode()+";node:"+node);
        int nhash = node.hashCode();
        Boolean ck = scopeck.get(nhash);
        if (ck != null) {
            throw new ConflictASTNodeHashCodeError("Conflict Scope.");
        }
        scopeck.put(nhash, true);
        sdm.EnterBlock(nhash);
        if (!(node instanceof LambdaExpression || node instanceof ForStatement)) {
            mw.push(new MethodWindow());
        }
    }

    protected void ExitBlock(ASTNode node) {
        if (!(node instanceof LambdaExpression || node instanceof ForStatement)) {
            mw.pop();
        }
        sdm.ExitBlock();
        scopeck.remove(node.hashCode());
    }

    protected void LabelReferPreHandle(SimpleName label) {
        if (label != null) {
            int namehashcode = label.hashCode();
            runpermit.AddNodeHelp(namehashcode, true);
            // referhint.AddNodeHelp(namehashcode,
            // ReferenceHintLibrary.LabelUse);
            AddNodeRefered(namehashcode, ReferenceHintLibrary.LabelUse);
        }
    }

    protected void LabelReferPostHandle(String prefix, SimpleName label) {
        String labelcode = null;
        if (label != null) {
            int namehashcode = label.hashCode();
            labelcode = referedcnt.GetNodeHelp(namehashcode);

            runpermit.DeleteNodeHelp(namehashcode);
            referhint.DeleteNodeHelp(namehashcode);
            DeleteNodeRefered(namehashcode);
        }
        String nodecode = prefix + (labelcode != null ? GCodeMetaInfo.CommonSplitter + labelcode : "");
        GenerateOneLine(nodecode, false, false, false, true, null);
    }

    protected void VariableDeclarationFragmentPreHandle(Expression iniexpr, SimpleName name, CDType typecode, List<Dimension> extradimens, boolean firstfragment) {
        if (typecode == null || typecode.equals("")) {
            typecode = GetVeryRecentDeclaredType();
        }
        CDType finaltypecode = GenerateVariableDeclarationType(typecode, extradimens);
        SetVeryRecentDeclaredType(finaltypecode);
        if (firstfragment) {
            GenerateOneLine(GCodeMetaInfo.VariableDeclarationHint + finaltypecode, false, false, false, true, null);
        }
        int namehashcode = name.hashCode();
        runforbid.AddNodeHelp(namehashcode, true);
        if (iniexpr != null) {
            referhint.AddNodeHelp(iniexpr.hashCode(), ReferenceHintLibrary.DataUse);
            // AddNodeRefered(iniexpr.hashCode());
            // if (!VeryRecentNotGenerateCode) {
            GenerateOneLine(GCodeMetaInfo.VariableDeclarationHolder + "=", true, true, false, true, null);
            // }
        } else {
            // if (!VeryRecentNotGenerateCode) {
            GenerateOneLine(GCodeMetaInfo.VariableDeclarationHolder, false, false, false, true, null);
            // }
        }
    }

    protected void VariableDeclarationFragmentPostHandle(Expression iniexpr, SimpleName name) {
        // if (!VeryRecentNotGenerateCode) {
        // GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfAStatement);
        // }
        // handle scope offset when end.
        int namehashcode = name.hashCode();
        int hint = ReferenceHintLibrary.DataDeclare;
        Boolean isfield = fielddeclared.GetNodeHelp(namehashcode);
        if (isfield != null && isfield == true) {
            hint = ReferenceHintLibrary.FieldDeclare;
        }
        runforbid.DeleteNodeHelp(namehashcode);
        referhint.AddNodeHelp(namehashcode, hint);
        runpermit.AddNodeHelp(namehashcode, true);
        visit(name);
        // delete hint
        referhint.DeleteNodeHelp(namehashcode);
        runpermit.DeleteNodeHelp(namehashcode);
        if (iniexpr != null) {
            // DeleteNodeRefered(iniexpr.hashCode());
            referhint.DeleteNodeHelp(iniexpr.hashCode());
        }
        SetVeryRecentDeclaredType(null);
    }

    protected void MethodPushReferRequest(Expression expr, List<ASTNode> args) {
        if (expr != null) {
            int exprhashcode = expr.hashCode();
            AddNodeRefered(exprhashcode, ReferenceHintLibrary.DataUpdate);
        }
        GenerateOneLine(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EnterMethodParam, false, false, false, true,
                null);
        if (expr != null) {
            argmutiple.push(false);
            AddFirstOrderTask(new FirstOrderTask(expr, null, expr.getParent(), true, false) {
                @Override
                public void run() {
                    if (argmutiple.pop()) {
                        GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfMethodPreRerferedExpression);
                    }
                }
            });
        }
        if (args != null && args.size() > 0) {
            Iterator<ASTNode> itr = args.iterator();
            while (itr.hasNext()) {
                ASTNode arg = itr.next();
                int arghashcode = arg.hashCode();
                // referhint.AddNodeHelp(arghashcode,
                // ReferenceHintLibrary.DataUse);
                AddNodeRefered(arghashcode, ReferenceHintLibrary.DataUse);
                argmutiple.push(false);
                AddFirstOrderTask(new FirstOrderTask(arg, null, arg.getParent(), true, false) {
                    @Override
                    public void run() {
                        if (argmutiple.pop()) {
                            GenerateEndInfo(GCodeMetaInfo.DescriptionHint + GCodeMetaInfo.EndOfMethodArgument);
                        }
                    }
                });
            }
        }
    }

    protected void MethodDeleteReferRequest(Expression expr, List<ASTNode> args) {
        if (expr != null) {
            int exprhashcode = expr.hashCode();
            DeleteNodeRefered(exprhashcode);
            referhint.DeleteNodeHelp(exprhashcode);
        }
        if (args != null && args.size() > 0) {
            Iterator<ASTNode> itr = args.iterator();
            while (itr.hasNext()) {
                ASTNode arg = itr.next();
                int arghashcode = arg.hashCode();
                DeleteNodeRefered(arghashcode);
                referhint.DeleteNodeHelp(arghashcode);
            }
        }
    }

    protected void MethodInvocationCode(String methodName, String invoker, List<ASTNode> args) {
        /*
         * if (methodName.equals("getCodeBase")) {
         * MyLogger.Info("==============hahaha=============="); }
         */
        StringBuilder nodecode = new StringBuilder("");
        nodecode.append(GCodeMetaInfo.MethodInvocationHint + methodName);
        String pre = "(";
        String post = ")";
        nodecode.append(pre);
        nodecode.append(invoker);
        Iterator<ASTNode> itr = args.iterator();
        while (itr.hasNext()) {
            nodecode.append(",");
            ASTNode arg = itr.next();
            String argcnt = referedcnt.GetNodeHelp(arg.hashCode());
            if (argcnt == null) {
                nodecode.append(GCodeMetaInfo.PreExist);
            } else {
                nodecode.append(argcnt);
            }
        }
        nodecode.append(post);
        CheckEnterMethodParam();
        GenerateOneLine(nodecode.toString(), false, false, false, true, null);
    }

    protected void EnumConstantInvocationCode(String enumConstantName, String invoker, List<Expression> arguments) {
        StringBuilder nodecode = new StringBuilder("");
        nodecode.append(GCodeMetaInfo.EnumConstantDeclarationHint + enumConstantName);
        String pre = "(";
        String post = ")";
        nodecode.append(pre);
        nodecode.append(invoker);
        if (arguments != null && arguments.size() > 0) {
            Iterator<Expression> itr = arguments.iterator();
            while (itr.hasNext()) {
                nodecode.append(",");
                ASTNode arg = itr.next();
                String argcnt = referedcnt.GetNodeHelp(arg.hashCode());
                if (argcnt == null) {
                    nodecode.append(GCodeMetaInfo.PreExist);
                } else {
                    nodecode.append(argcnt);
                }
            }
        }
        nodecode.append(post);
        CheckEnterMethodParam();
        GenerateOneLine(nodecode.toString(), false, false, false, true, null);
    }

    protected void ErrorAndStop(String info) {
        MyLogger.Error(info);
        MyLogger.Error("The system will exit.");
        new Exception().printStackTrace();
        System.exit(1);
    }

    protected void ResetDLM() {
        sdm.ResetCurrentClassField();
    }

    public ArrayList<String> GetMainAnalyseList(boolean isInAnonymous) {
        if (isInAnonymous) {
            return acp.GetRecentAnalyseList();
        } else {
            return ojfc.toList();
        }
    }

    protected void NewVariableDeclared(SimpleName name, Type tp) {
        NewVariableDeclared(name, GenCDType(tp));
    }

    protected void NewVariableDeclared(SimpleName name, CDType tp) {
        int namehashcode = name.hashCode();
        int hint = ReferenceHintLibrary.DataDeclare;
        Boolean isfield = fielddeclared.GetNodeHelp(namehashcode);
        if (isfield != null && isfield == true) {
            hint = ReferenceHintLibrary.FieldDeclare;
        }
        runpermit.AddNodeHelp(namehashcode, true);
        referhint.AddNodeHelp(namehashcode, hint);
        SetVeryRecentDeclaredType(tp);
        visit(name);
        SetVeryRecentDeclaredType(null);
        runpermit.DeleteNodeHelp(namehashcode);
        referhint.DeleteNodeHelp(namehashcode);
    }

    protected boolean NeedSpecialTreat(ASTNode node) {
        if ((node instanceof AbstractTypeDeclaration) || (node instanceof AnonymousClassDeclaration)
                || (node instanceof LambdaExpression) || (node instanceof ForStatement)) {
            return true;
        }
        return false;
    }

}