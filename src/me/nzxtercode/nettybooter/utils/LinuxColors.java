package me.nzxtercode.nettybooter.utils;

import java.util.Random;

/**
 * The type Linux colors.
 */
public class LinuxColors {
    /**
     * The constant SUPPORT.
     */
    public static final boolean SUPPORT = !System.getProperty("os.name").toLowerCase().contains("win");

    /**
     * The constant RESET.
     */
    public static final String RESET = "\033[0m";

    /**
     * The constant BLACK.
     */
    public static final String BLACK = "\033[0;30m";

    /**
     * The constant RED.
     */
    public static final String RED = "\033[0;31m";

    /**
     * The constant GREEN.
     */
    public static final String GREEN = "\033[0;32m";

    /**
     * The constant YELLOW.
     */
    public static final String YELLOW = "\033[0;33m";

    /**
     * The constant BLUE.
     */
    public static final String BLUE = "\033[0;34m";

    /**
     * The constant PURPLE.
     */
    public static final String PURPLE = "\033[0;35m";

    /**
     * The constant CYAN.
     */
    public static final String CYAN = "\033[0;36m";

    /**
     * The constant WHITE.
     */
    public static final String WHITE = "\033[0;37m";

    /**
     * The constant BLACK_BOLD.
     */
    public static final String BLACK_BOLD = "\033[1;30m";

    /**
     * The constant RED_BOLD.
     */
    public static final String RED_BOLD = "\033[1;31m";

    /**
     * The constant GREEN_BOLD.
     */
    public static final String GREEN_BOLD = "\033[1;32m";

    /**
     * The constant YELLOW_BOLD.
     */
    public static final String YELLOW_BOLD = "\033[1;33m";

    /**
     * The constant BLUE_BOLD.
     */
    public static final String BLUE_BOLD = "\033[1;34m";

    /**
     * The constant PURPLE_BOLD.
     */
    public static final String PURPLE_BOLD = "\033[1;35m";

    /**
     * The constant CYAN_BOLD.
     */
    public static final String CYAN_BOLD = "\033[1;36m";

    /**
     * The constant WHITE_BOLD.
     */
    public static final String WHITE_BOLD = "\033[1;37m";

    /**
     * The constant BLACK_UNDERLINED.
     */
    public static final String BLACK_UNDERLINED = "\033[4;30m";

    /**
     * The constant RED_UNDERLINED.
     */
    public static final String RED_UNDERLINED = "\033[4;31m";

    /**
     * The constant GREEN_UNDERLINED.
     */
    public static final String GREEN_UNDERLINED = "\033[4;32m";

    /**
     * The constant YELLOW_UNDERLINED.
     */
    public static final String YELLOW_UNDERLINED = "\033[4;33m";

    /**
     * The constant BLUE_UNDERLINED.
     */
    public static final String BLUE_UNDERLINED = "\033[4;34m";

    /**
     * The constant PURPLE_UNDERLINED.
     */
    public static final String PURPLE_UNDERLINED = "\033[4;35m";

    /**
     * The constant CYAN_UNDERLINED.
     */
    public static final String CYAN_UNDERLINED = "\033[4;36m";

    /**
     * The constant WHITE_UNDERLINED.
     */
    public static final String WHITE_UNDERLINED = "\033[4;37m";

    /**
     * The constant BLACK_BACKGROUND.
     */
    public static final String BLACK_BACKGROUND = "\033[40m";

    /**
     * The constant RED_BACKGROUND.
     */
    public static final String RED_BACKGROUND = "\033[41m";

    /**
     * The constant GREEN_BACKGROUND.
     */
    public static final String GREEN_BACKGROUND = "\033[42m";

    /**
     * The constant YELLOW_BACKGROUND.
     */
    public static final String YELLOW_BACKGROUND = "\033[43m";

    /**
     * The constant BLUE_BACKGROUND.
     */
    public static final String BLUE_BACKGROUND = "\033[44m";

    /**
     * The constant PURPLE_BACKGROUND.
     */
    public static final String PURPLE_BACKGROUND = "\033[45m";

    /**
     * The constant CYAN_BACKGROUND.
     */
    public static final String CYAN_BACKGROUND = "\033[46m";

    /**
     * The constant WHITE_BACKGROUND.
     */
    public static final String WHITE_BACKGROUND = "\033[47m";

    /**
     * The constant BLACK_BRIGHT.
     */
    public static final String BLACK_BRIGHT = "\033[0;90m";

    /**
     * The constant RED_BRIGHT.
     */
    public static final String RED_BRIGHT = "\033[0;91m";

    /**
     * The constant GREEN_BRIGHT.
     */
    public static final String GREEN_BRIGHT = "\033[0;92m";

    /**
     * The constant YELLOW_BRIGHT.
     */
    public static final String YELLOW_BRIGHT = "\033[0;93m";

    /**
     * The constant BLUE_BRIGHT.
     */
    public static final String BLUE_BRIGHT = "\033[0;94m";

    /**
     * The constant PURPLE_BRIGHT.
     */
    public static final String PURPLE_BRIGHT = "\033[0;95m";

    /**
     * The constant CYAN_BRIGHT.
     */
    public static final String CYAN_BRIGHT = "\033[0;96m";

    /**
     * The constant WHITE_BRIGHT.
     */
    public static final String WHITE_BRIGHT = "\033[0;97m";

    /**
     * The constant BLACK_BOLD_BRIGHT.
     */
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";

    /**
     * The constant RED_BOLD_BRIGHT.
     */
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";

    /**
     * The constant GREEN_BOLD_BRIGHT.
     */
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";

    /**
     * The constant YELLOW_BOLD_BRIGHT.
     */
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";

    /**
     * The constant BLUE_BOLD_BRIGHT.
     */
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";

    /**
     * The constant PURPLE_BOLD_BRIGHT.
     */
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";

    /**
     * The constant CYAN_BOLD_BRIGHT.
     */
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";

    /**
     * The constant WHITE_BOLD_BRIGHT.
     */
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";

    /**
     * The constant BLACK_BACKGROUND_BRIGHT.
     */
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";

    /**
     * The constant RED_BACKGROUND_BRIGHT.
     */
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";

    /**
     * The constant GREEN_BACKGROUND_BRIGHT.
     */
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";

    /**
     * The constant YELLOW_BACKGROUND_BRIGHT.
     */
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";

    /**
     * The constant BLUE_BACKGROUND_BRIGHT.
     */
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";

    /**
     * The constant PURPLE_BACKGROUND_BRIGHT.
     */
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";

    /**
     * The constant CYAN_BACKGROUND_BRIGHT.
     */
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";

    /**
     * The constant WHITE_BACKGROUND_BRIGHT.
     */
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";

    /**
     * The constant colors.
     */
    public static final String[] colors = new String[] { "\033[0;94m", "\033[0;36m", "\033[0;96m", "\033[0;92m",
			"\033[0;95m", "\033[0;31m", "\033[0;91m", "\033[0;33m", "\033[0;93m", "\033[0;37m", "\033[0;97m" };

    /**
     * The constant r.
     */
    public static final Random r = new Random();

    /**
     * Gets random color.
     *
     * @return the random color
     */
    public static String getRandomColor() {
		return colors[r.nextInt(colors.length - 1)];
	}
}
