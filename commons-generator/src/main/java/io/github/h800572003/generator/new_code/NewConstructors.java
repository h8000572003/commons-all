package io.github.h800572003.generator.new_code;

import io.github.h800572003.generator.ICode;
import io.github.h800572003.generator.contract.Protecteds;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class NewConstructors implements ICode {


    private String name;

    private NewClass newClass;

    private List<NewConstructorGroup> constructors = new ArrayList<>();

    public NewConstructors(String name,NewClass newClass) {
        this.name = name;
        this.newClass=newClass;
    }
    public NewClass getRoot(){
        return this.newClass;
    }

    @Override
    public String get() {
        GroupCode groupCode = new GroupCode();
        constructors.forEach(i->groupCode.add(i));
        return groupCode.get();
    }

    @Override
    public String toString() {
        return this.get();
    }

    public NewConstructorGroup createArgs() {
        NewConstructorGroup newConstructorGroup = new NewConstructorGroup(this);
        constructors.add(newConstructorGroup);
        return newConstructorGroup;
    }
    public NewClass backNewClass(){
        return this.newClass;
    }



    public static class NewConstructorGroup implements ICode {

        private List<NewConstructorField> constructors = new ArrayList<>();
        private NewConstructors root;

        private Protecteds protecteds=Protecteds.PUBLIC;

        private GroupCode body=new GroupCode();

        public NewConstructorGroup(NewConstructors root) {
            this.root = root;
        }

        public NewConstructors getRoot() {
            return root;
        }


        @Override
        public String get() {
            StringBuffer buffer=new StringBuffer();
            buffer.append(protecteds.toJavaName()+" ");
            buffer.append(root.name+"("+ StringUtils.join(constructors,",")+") {\n");
            buffer.append(body.get());
            buffer.append("\n");
            buffer.append("}");
            return buffer.toString();
        }

        public NewConstructorGroup add(String string, String name) {
            this.constructors.add(new NewConstructorField(string, name, root));
            return this;
        }

        public NewConstructorGroup addBody(String s) {
            body.add(new NewStringLine(s,true));
            return this;
        }
        public NewConstructorGroup addBody(ICode code) {
            body.add(code);
            return this;
        }

        @Override
        public String toString() {
            return this.get();
        }
        public NewClass backNewClass(){
            return this.root.newClass;
        }

    }

    public static class NewConstructorField implements ICode {
        private String type;
        private String name;

        private NewConstructors root;

        public NewConstructorField(String type, String name, NewConstructors root) {
            this.type = type;
            this.name = name;
            this.root = root;
        }

        public NewConstructors getRoot() {
            return root;
        }

        @Override
        public String get() {
            return String.format("%s %s", type, name);
        }

        @Override
        public String toString() {
            return this.get();
        }
        public NewClass backNewClass(){
            return this.root.newClass;
        }

    }
}
