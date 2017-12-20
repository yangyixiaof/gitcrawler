package cn.yyx.research.language.simplified.JDTManager;

import java.util.ArrayList;
import java.util.Iterator;

import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.JDTManager.NodeCode;
import cn.yyx.research.language.Utility.NormalLibrary;

public abstract class JavaCode {

    protected StringBuilder sb = new StringBuilder("");
    protected ArrayList<String> codes = new ArrayList<String>();

    public abstract void AddOneMethodNodeCode(NodeCode nc);

    public abstract void OneSentenceEnd();

    public void AddOneNodeCode(NodeCode nc) {
        Iterator<String> itr = nc.GetCodeIterator();
        String prestr = null;
        while (itr.hasNext()) {
            String onesentence = itr.next();
            onesentence = NormalLibrary.normalize(onesentence);
            CheckAllHavePrefixHint(onesentence, prestr);
            prestr = onesentence;
            sb.append(" " + onesentence);
            codes.add(onesentence);
        }
    }

    public int getAllWords() {
        return codes.size();
    }

    public boolean IsEmpty() {
        return sb.length() == 0;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public ArrayList<String> toList() {
        return codes;
    }

    public void CheckAllHavePrefixHint(String onesentence, String prestr) {
        int atidx = onesentence.indexOf('@');
        if (atidx < 0) {
            System.err.println("There is no @ in str, What is the problem? The wrong sentence is:" + onesentence);
            System.exit(1);
        }
        String prefixhint = onesentence.substring(0, atidx + 1);
        if (GCodeMetaInfo.AnonymousClassBegin.equals(prefixhint) || GCodeMetaInfo.AnonymousClassPreHint.equals(prefixhint) || GCodeMetaInfo.ATInterfaceHint.equals(prefixhint) || GCodeMetaInfo.AnnotationTypeMemberDeclarationHint.equals(prefixhint) || GCodeMetaInfo.ClassDeclarationHint.equals(prefixhint) || GCodeMetaInfo.ClassInnerDeclarationHint.equals(prefixhint) || GCodeMetaInfo.EnumDeclarationHint.equals(prefixhint) || GCodeMetaInfo.MethodDeclarationHint.equals(prefixhint) || GCodeMetaInfo.EnumConstantDeclarationHint.equals(prefixhint) || GCodeMetaInfo.LabelDeclarationHint.equals(prefixhint) || GCodeMetaInfo.VariableDeclarationHint.equals(prefixhint) || GCodeMetaInfo.LambdaExpressionHint.equals(prefixhint) || GCodeMetaInfo.MethodReferenceHint.equals(prefixhint) || GCodeMetaInfo.CastExpressionHint.equals(prefixhint) || GCodeMetaInfo.AssignmentHint.equals(prefixhint) || GCodeMetaInfo.BreakHint.equals(prefixhint) || GCodeMetaInfo.ContinueHint.equals(prefixhint) || GCodeMetaInfo.DoWhileHint.equals(prefixhint) || GCodeMetaInfo.InfixExpressionHint.equals(prefixhint) || GCodeMetaInfo.InstanceofExpressionHint.equals(prefixhint) || GCodeMetaInfo.PostfixExpressionHint.equals(prefixhint) || GCodeMetaInfo.PrefixExpressionHint.equals(prefixhint) || GCodeMetaInfo.ReturnHint.equals(prefixhint) || GCodeMetaInfo.SwitchHint.equals(prefixhint) || GCodeMetaInfo.CaseHint.equals(prefixhint) || GCodeMetaInfo.DefaultHint.equals(prefixhint) || GCodeMetaInfo.SynchronizedHint.equals(prefixhint) || GCodeMetaInfo.ThrowStatementHint.equals(prefixhint) || GCodeMetaInfo.CatchHint.equals(prefixhint) || GCodeMetaInfo.WhileStatementHint.equals(prefixhint) || GCodeMetaInfo.IfStatementHint.equals(prefixhint) || GCodeMetaInfo.MethodInvocationHint.equals(prefixhint) || GCodeMetaInfo.ArrayCreationHint.equals(prefixhint) || GCodeMetaInfo.LiteralHint.equals(prefixhint) || GCodeMetaInfo.NameHint.equals(prefixhint) || GCodeMetaInfo.QualifiedNameHint.equals(prefixhint) || GCodeMetaInfo.FieldAccessHint.equals(prefixhint) || GCodeMetaInfo.QualifiedHint.equals(prefixhint) || GCodeMetaInfo.Initializer.equals(prefixhint) || GCodeMetaInfo.DescriptionHint.equals(prefixhint) || GCodeMetaInfo.VariableDeclarationHolder.equals(prefixhint) || GCodeMetaInfo.EnhancedFor.equals(prefixhint) || GCodeMetaInfo.ArrayAccess.equals(prefixhint)) {
        } else {
            System.err.println("Unrecognized code prefix hint, What is the problem? The wrong sentence is:" + onesentence + ";prestr:" + prestr);
            System.exit(1);

        }
    }
}