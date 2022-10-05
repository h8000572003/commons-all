package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import io.github.h800572003.generator.utils.FreeMarkerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NewMethods  implements  ICode{

    private List<NewMethod> newMethods = new ArrayList<>();

    private NewClass root;

    public NewMethods(NewClass root) {
        this.root = root;
    }

    public NewClass getPrevious() {
        return this.root;
    }


    public NewMethod createMethod(Protecteds aPublic, String name) {
        final NewMethod newMethod = new NewMethod(aPublic, name, this);
        newMethods.add(newMethod);

        optionIAdd(newMethod);
        return newMethod;
    }

    public NewMethod createMethod(Protecteds aPublic, String name, String returnValue) {
        final NewMethod newMethod = new NewMethod(aPublic, name, returnValue, this);
        newMethods.add(newMethod);
        optionIAdd(newMethod);
        return newMethod;
    }

    private void optionIAdd(NewMethod newMethod) {
        if(root!=null){
            root.addBody(newMethod);
        }
    }

    @Override
    public String get() {
        return StringUtils.join(newMethods,"\n");
    }


    public static class NewMethod implements ICode {

        private String protectedValue = Protecteds.PROTECTED.toJavaName();
        private final String name;
        private final String returnType;

        private NewMethods root;
        private String memo = "";

        private final GroupCode annotations = new GroupCode();

        private final GroupCode body = new GroupCode();

        private final MethodArgs args = new MethodArgs();

        private final NewExceptions exceptions = new NewExceptions();


        public NewMethod(Protecteds protectedValue, String name, NewMethods root) {
            this(protectedValue, "void", name, root);
        }

        public NewMethod addBody(ICode code) {
            this.body.add(code);
            return this;
        }

        public NewMethod addBody(String text) {
            this.body.add(new NewStringLine(text));
            return this;
        }

        public NewMethod addAnnotation(NewAnnotation newAnnotation) {
            annotations.add(newAnnotation);
            return this;
        }

        public NewMethod(Protecteds protectedValue, String returnType, String name,NewMethods newMethods) {
            this.protectedValue = protectedValue.toJavaName();
            this.name = name;
            this.returnType = returnType;
            this.root=newMethods;

        }

        public NewMethod addMethodArg(MethodArgs.MethodArg methodArg) {
            this.args.add(methodArg);
            return this;
        }

        @Override
        public String get() {
            return
                    FreeMarkerUtils.toString(getClass().getSimpleName() + ".ftl", this);
        }


        public String getArgs() {
            return args.get();
        }

        public String getProtectedValue() {
            return protectedValue;
        }

        public String getName() {
            return name;
        }

        public String getReturnType() {
            return returnType;
        }

        public String getMemo() {
            return memo;
        }

        public NewMethod setMemo(String memo) {
            this.memo = memo;
            return this;
        }

        public String getHeader() {
            final GroupCode body = new GroupCode();
            if (StringUtils.isNoneBlank(memo)) {
                body.add(new NewComment(true, memo));
            }
            body.add(annotations);
            return body.get();
        }

        public String getBody() {
            return body.get();
        }


        public NewExceptions getExceptions() {
            return exceptions;
        }

        @Override
        public String toString() {
            return get();
        }

        public NewMethod addThrow(String throwable) {
            exceptions.add(throwable);
            return this;
        }

        public NewMethods getPrevious(){
            return this.root;
        }
    }
}
