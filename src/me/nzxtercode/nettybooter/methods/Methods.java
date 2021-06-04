package me.nzxtercode.nettybooter.methods;

import java.util.HashMap;

import me.nzxtercode.nettybooter.methods.impl.PacketOutOfRange;
import me.nzxtercode.nettybooter.methods.impl.Pinger;
import me.nzxtercode.nettybooter.methods.impl.ConsoleSpammer;
import me.nzxtercode.nettybooter.methods.impl.Joiner;
import me.nzxtercode.nettybooter.methods.impl.Nullping;
import me.nzxtercode.nettybooter.methods.impl.RandomExecptions;
import me.nzxtercode.nettybooter.methods.impl.QuitExceptions;
import me.nzxtercode.nettybooter.methods.interfaces.IMethod;

/**
 * The type Methods.
 */
public class Methods {

    /**
     * The constant METHODS.
     */
    public static final HashMap<Integer, IMethod> METHODS = new HashMap<>();

    /**
     * Gets by id.
     *
     * @param i the
     * @return the by id
     */
    public static IMethod getByID(int i) {
		return METHODS.getOrDefault(i, (c, p) -> {
			c.close();
			System.err.println("invalid method id: " + i);
		});
	}

	private static void registerMethod(int i, IMethod m) {
		if (METHODS.containsKey(i))
			throw new IllegalStateException("Method with id " + i + " is already existing.");
		METHODS.put(i, m);
	}

    /**
     * Init.
     */
    public static void init() {
		registerMethod(1, new Pinger());
		registerMethod(2, new QuitExceptions());
		registerMethod(3, new RandomExecptions());
		registerMethod(4, new PacketOutOfRange());
		registerMethod(5, new Joiner());
		registerMethod(6, new Nullping());
		registerMethod(7, new ConsoleSpammer());
	}
}
