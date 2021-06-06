package me.nzxtercode.nettybooter.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Linux colors.
 */
public enum LinuxColors {

    /**
     * The constant RESET.
     */
    RESET("\033[0m"),

    /**
     * The constant BLACK.
     */
    BLACK("\033[0;30m"),

    /**
     * The constant RED.
     */
    RED("\033[0;31m"),

    /**
     * The constant GREEN.
     */
    GREEN("\033[0;32m"),

    /**
     * The constant YELLOW.
     */
    YELLOW("\033[0;33m"),

    /**
     * The constant BLUE.
     */
    BLUE("\033[0;34m"),

    /**
     * The constant PURPLE.
     */
    PURPLE("\033[0;35m"),

    /**
     * The constant CYAN.
     */
    CYAN("\033[0;36m"),

    /**
     * The constant WHITE.
     */
    WHITE("\033[0;37m"),

    /**
     * The constant BLACK_BOLD.
     */
    BLACK_BOLD("\033[1;30m"),

    /**
     * The constant RED_BOLD.
     */
    RED_BOLD("\033[1;31m"),

    /**
     * The constant GREEN_BOLD.
     */
    GREEN_BOLD("\033[1;32m"),

    /**
     * The constant YELLOW_BOLD.
     */
    YELLOW_BOLD("\033[1;33m"),

    /**
     * The constant BLUE_BOLD.
     */
    BLUE_BOLD("\033[1;34m"),

    /**
     * The constant PURPLE_BOLD.
     */
    PURPLE_BOLD("\033[1;35m"),

    /**
     * The constant CYAN_BOLD.
     */
    CYAN_BOLD("\033[1;36m"),

    /**
     * The constant WHITE_BOLD.
     */
    WHITE_BOLD("\033[1;37m"),

    /**
     * The constant BLACK_UNDERLINED.
     */
    BLACK_UNDERLINED("\033[4;30m"),

    /**
     * The constant RED_UNDERLINED.
     */
    RED_UNDERLINED("\033[4;31m"),

    /**
     * The constant GREEN_UNDERLINED.
     */
    GREEN_UNDERLINED("\033[4;32m"),

    /**
     * The constant YELLOW_UNDERLINED.
     */
    YELLOW_UNDERLINED("\033[4;33m"),

    /**
     * The constant BLUE_UNDERLINED.
     */
    BLUE_UNDERLINED("\033[4;34m"),

    /**
     * The constant PURPLE_UNDERLINED.
     */
    PURPLE_UNDERLINED("\033[4;35m"),

    /**
     * The constant CYAN_UNDERLINED.
     */
    CYAN_UNDERLINED("\033[4;36m"),

    /**
     * The constant WHITE_UNDERLINED.
     */
    WHITE_UNDERLINED("\033[4;37m"),

    /**
     * The constant BLACK_BACKGROUND.
     */
    BLACK_BACKGROUND("\033[40m"),

    /**
     * The constant RED_BACKGROUND.
     */
    RED_BACKGROUND("\033[41m"),

    /**
     * The constant GREEN_BACKGROUND.
     */
    GREEN_BACKGROUND("\033[42m"),

    /**
     * The constant YELLOW_BACKGROUND.
     */
    YELLOW_BACKGROUND("\033[43m"),

    /**
     * The constant BLUE_BACKGROUND.
     */
    BLUE_BACKGROUND("\033[44m"),

    /**
     * The constant PURPLE_BACKGROUND.
     */
    PURPLE_BACKGROUND("\033[45m"),

    /**
     * The constant CYAN_BACKGROUND.
     */
    CYAN_BACKGROUND("\033[46m"),

    /**
     * The constant WHITE_BACKGROUND.
     */
    WHITE_BACKGROUND("\033[47m"),

    /**
     * The constant BLACK_BRIGHT.
     */
    BLACK_BRIGHT("\033[0;90m"),

    /**
     * The constant RED_BRIGHT.
     */
    RED_BRIGHT("\033[0;91m"),

    /**
     * The constant GREEN_BRIGHT.
     */
    GREEN_BRIGHT("\033[0;92m"),

    /**
     * The constant YELLOW_BRIGHT.
     */
    YELLOW_BRIGHT("\033[0;93m"),

    /**
     * The constant BLUE_BRIGHT.
     */
    BLUE_BRIGHT("\033[0;94m"),

    /**
     * The constant PURPLE_BRIGHT.
     */
    PURPLE_BRIGHT("\033[0;95m"),

    /**
     * The constant CYAN_BRIGHT.
     */
    CYAN_BRIGHT("\033[0;96m"),

    /**
     * The constant WHITE_BRIGHT.
     */
    WHITE_BRIGHT("\033[0;97m"),

    /**
     * The constant BLACK_BOLD_BRIGHT.
     */
    BLACK_BOLD_BRIGHT("\033[1;90m"),

    /**
     * The constant RED_BOLD_BRIGHT.
     */
    RED_BOLD_BRIGHT("\033[1;91m"),

    /**
     * The constant GREEN_BOLD_BRIGHT.
     */
    GREEN_BOLD_BRIGHT("\033[1;92m"),

    /**
     * The constant YELLOW_BOLD_BRIGHT.
     */
    YELLOW_BOLD_BRIGHT("\033[1;93m"),

    /**
     * The constant BLUE_BOLD_BRIGHT.
     */
    BLUE_BOLD_BRIGHT("\033[1;94m"),

    /**
     * The constant PURPLE_BOLD_BRIGHT.
     */
    PURPLE_BOLD_BRIGHT("\033[1;95m"),

    /**
     * The constant CYAN_BOLD_BRIGHT.
     */
    CYAN_BOLD_BRIGHT("\033[1;96m"),

    /**
     * The constant WHITE_BOLD_BRIGHT.
     */
    WHITE_BOLD_BRIGHT("\033[1;97m"),

    /**
     * The constant BLACK_BACKGROUND_BRIGHT.
     */
    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),

    /**
     * The constant RED_BACKGROUND_BRIGHT.
     */
    RED_BACKGROUND_BRIGHT("\033[0;101m"),

    /**
     * The constant GREEN_BACKGROUND_BRIGHT.
     */
    GREEN_BACKGROUND_BRIGHT("\033[0;102m"),

    /**
     * The constant YELLOW_BACKGROUND_BRIGHT.
     */
    YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),

    /**
     * The constant BLUE_BACKGROUND_BRIGHT.
     */
    BLUE_BACKGROUND_BRIGHT("\033[0;104m"),

    /**
     * The constant PURPLE_BACKGROUND_BRIGHT.
     */
    PURPLE_BACKGROUND_BRIGHT("\033[0;105m"),

    /**
     * The constant CYAN_BACKGROUND_BRIGHT.
     */
    CYAN_BACKGROUND_BRIGHT("\033[0;106m"),

    /**
     * The constant WHITE_BACKGROUND_BRIGHT.
     */
    WHITE_BACKGROUND_BRIGHT("\033[0;107m"),
    
    EMPTY("");
    
    public final boolean support = !System.getProperty("os.name").toLowerCase().contains("win");
    
    /**
     * The constant colors.
     */
    public final String[] colors = new String[] { "\033[0;94m", "\033[0;36m", "\033[0;96m", "\033[0;92m",
			"\033[0;95m", "\033[0;31m", "\033[0;91m", "\033[0;33m", "\033[0;93m", "\033[0;37m", "\033[0;97m" };

    private final String code;

    LinuxColors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    /**
     * Gets random color.
     *
     * @return the random color
     */
    public String getRandomColor() {
		return colors[ThreadLocalRandom.current().nextInt(colors.length - 1)];
	}
}
