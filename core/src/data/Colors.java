package data;

public enum Colors {
    PINK(1),
    PURPLE(2),
    YELLOW(3),
    LIGHT_PURPLE(4);

    private final int code;

    Colors(int code) {
        this.code = code;
    }

    public static int getMax() {
        int max = 0;
        for (Colors itr : values()) {
            max = itr.getCode();
        }
        return max;
    }

    public int getCode() {
        return code;
    }

    public static Colors searchByCode(int code) {
        for (Colors color : values()) {
            if (color.getCode() == code)
                return color;
        }
        return Colors.PINK;
    }

    @Override
    public String toString() {
        //System.out.println(this.name().toLowerCase());
        return this.name().toLowerCase();

    }
}
