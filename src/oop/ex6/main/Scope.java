import java.util.HashMap;

public abstract class Scope {

    /** The variables that were defined locally in the statement. */
    HashMap<String, Variable> variables;

    /** The statement whose scope encapsulates this statement. */
    Scope parent;

    /** The global statement this statement is a part of. */
//    GlobalStatement global;
}
