package net.hycrafthd.umod;

public class Logger {

    private static final LevelOfDetail MODE = LevelOfDetail.INFO;

    private static final String StdOutput = "[" + UReference.name + "/";

    private static void print(String method, String message, LevelOfDetail in) {
//        UMod.log.info(StdOutput + in.toString() + "] " + method + ": " + message);
    	
    	switch (in) {
		case INFO:
			UMod.log.info(StdOutput + in.toString() + "] " + method + ": " + message);
			break;
		case DEBUG:
			UMod.log.info(StdOutput + in.toString() + "] " + method + ": " + message);
			break;
		case WARN:
			UMod.log.warn(StdOutput + in.toString() + "] " + method + ": " + message);
			break;
		case ERROR:
			UMod.log.error(StdOutput + in.toString() + "] " + method + ": " + message);
			break;
		case NONE:
			UMod.log.info(StdOutput + in.toString() + "] " + method + ": " + message);
			break;
		default:
			break;
		}
    	
    }

    public static void info(String method, String message) {
        if (MODE.getLevel() <= 0) {
            print(method, message, LevelOfDetail.INFO);
        }
    }

    public static void info(String message) {
        if (MODE.getLevel() <= 0) {
            print("", message, LevelOfDetail.INFO);
        }
    }

    public static void info(Class<?> clazz, String method, String message) {
        if (MODE.getLevel() <= 0) {
            print(clazz.getSimpleName() + "/" + method, message, LevelOfDetail.INFO);
        }
    }
    
    public static void debug(String method, String message) {
        if (MODE.getLevel() == 1) {
            print(method, message, LevelOfDetail.DEBUG);
        }
    }

    public static void debug(Class<?> clazz, String method, String message) {
        if (MODE.getLevel() == 1) {
            print(clazz.getSimpleName() + "/" + method, message, LevelOfDetail.DEBUG);
        }
    }
    
    public static void warn(String method, String message) {
        if (MODE.getLevel() <= 2) {
            print(method, message, LevelOfDetail.WARN);
        }
    }

    public static void warn(Class<?> clazz, String method, String message) {
        if (MODE.getLevel() <= 2) {
            print(clazz.getSimpleName() + "/" + method, message, LevelOfDetail.WARN);
        }
    }
    
    public static void error(String method, String message) {
        if (MODE.getLevel() <= 3) {
            print(method, message, LevelOfDetail.ERROR);
        }
    }

    public static void error(Class<?> clazz, String method, String message) {
        if (MODE.getLevel() <= 3) {
            print(clazz.getSimpleName() + "/" + method, message, LevelOfDetail.ERROR);
        }
    }
    
    /**
     * Define Level Of Detail with fixed numbers.
     */
    private enum LevelOfDetail {
        INFO(0), DEBUG(1), WARN(2), ERROR(3), NONE(4);

        private final int level;

        LevelOfDetail(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
	
}
