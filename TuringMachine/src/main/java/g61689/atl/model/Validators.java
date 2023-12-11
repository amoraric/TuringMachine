package g61689.atl.model;

import java.util.Arrays;
import java.util.Objects;

class Validator1 extends Validator {
    private final int secretCode;
    public Validator1(int secretCode) {
        super("Compares the first digit of the code with 1");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 0);
        if (digit > 1) {
            return "Greater than 1";
        } else if (digit == 1) {
            return "Equal to 1";
        } else {
            return "Less than 1";
        }
    }
}

class Validator2 extends Validator {
    private final int secretCode;
    public Validator2(int secretCode) {
        super("Compare the first digit of the code with 3");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 0);
        if (digit > 3) {
            return "Greater than 3";
        } else if (digit == 3) {
            return "Equal to 3";
        } else {
            return "Less than 3";
        }
    }
}

class Validator3 extends Validator {
    private final int secretCode;
    public Validator3(int secretCode) {
        super("Compare the second digit of the code with 3");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 1);
        if (digit > 3) {
            return "Greater than 3";
        } else if (digit == 3) {
            return "Equal to 3";
        } else {
            return "Less than 3";
        }
    }
}

class Validator4 extends Validator {
    private final int secretCode;
    public Validator4(int secretCode) {
        super("Compare the second digit of the code with 4");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 1);
        if (digit > 4) {
            return "Greater than 4";
        } else if (digit == 4) {
            return "Equal to 4";
        } else {
            return "Less than 4";
        }
    }
}

class Validator5 extends Validator {
    private final int secretCode;
    public Validator5(int secretCode) {
        super("Checks the parity of the first digit of the code");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 0);
        if (digit % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }
}

class Validator6 extends Validator {
    private final int secretCode;
    public Validator6(int secretCode) {
        super("Checks the parity of the second digit");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 1);
        if (digit % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }
}

class Validator7 extends Validator {
    private final int secretCode;
    public Validator7(int secretCode) {
        super("Checks the parity of the third digit");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int digit = getDigit(code, 2);
        if (digit % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }
}

class Validator8 extends Validator {
    private final int secretCode;
    public Validator8(int secretCode) {
        super("Counts how many times the value 1 appears in the code");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        long count = Arrays.stream(codeArray).filter(value -> value == 1).count();
        if (count == 0) {
            return "Not once";
        } else if (count == 1) {
            return "Once";
        } else if (count == 2) {
            return "Twice";
        } else {
            return "Thrice";
        }
    }
}

class Validator9 extends Validator {
    private final int secretCode;
    public Validator9(int secretCode) {
        super("Counts how many times the value 3 appears in the code");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        long count = Arrays.stream(codeArray).filter(value -> value == 3).count();
        if (count == 0) {
            return "Not once";
        } else if (count == 1) {
            return "Once";
        } else if (count == 2) {
            return "Twice";
        } else {
            return "Thrice";
        }
    }
}

class Validator10 extends Validator {
    private final int secretCode;
    public Validator10(int secretCode) {
        super("Counts how many times the value 4 appears in the code");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        long count = Arrays.stream(codeArray).filter(value -> value == 4).count();
        if (count == 0) {
            return "Not once";
        } else if (count == 1) {
            return "Once";
        } else if (count == 2) {
            return "Twice";
        } else {
            return "Thrice";
        }
    }
}

class Validator11 extends Validator {
    private final int secretCode;
    public Validator11(int secretCode) {
        super("Compares the first digit of the code with the second");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int firstDigit = getDigit(code, 0);
        int secondDigit = getDigit(code, 1);
        if (firstDigit > secondDigit) {
            return "Greater";
        } else if (firstDigit == secondDigit) {
            return "Equal";
        } else {
            return "Smaller";
        }
    }
}

class Validator12 extends Validator {
    private final int secretCode;
    public Validator12(int secretCode) {
        super("Compare the first digit of the code with the third");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int firstDigit = getDigit(code, 0);
        int secondDigit = getDigit(code, 2);
        if (firstDigit > secondDigit) {
            return "Greater";
        } else if (firstDigit == secondDigit) {
            return "Equal";
        } else {
            return "Smaller";
        }
    }
}

class Validator13 extends Validator {
    private final int secretCode;
    public Validator13(int secretCode) {
        super("Compare the second digit of the code with the third");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int firstDigit = getDigit(code, 1);
        int secondDigit = getDigit(code, 2);
        if (firstDigit > secondDigit) {
            return "Greater";
        } else if (firstDigit == secondDigit) {
            return "Equal";
        } else {
            return "Smaller";
        }
    }
}

class Validator14 extends Validator {
    private final int secretCode;
    public Validator14(int secretCode) {
        super("Determines which number is strictly the smallest");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        int smallestIndex = 0;
        for (int i = 1; i < codeArray.length; i++) {
            if (codeArray[i] < codeArray[smallestIndex]) {
                smallestIndex = i;
            }
        }
        if (smallestIndex == 0) {
            return "First digit";
        } else if (smallestIndex == 1) {
            return "Second digit";
        } else if (smallestIndex == 2) {
            return "Third digit";
        } else {
            return "No digit";
        }
    }
}

class Validator15 extends Validator {
    private final int secretCode;
    public Validator15(int secretCode) {
        super("Determines which number is strictly the greatest");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        int greatestIndex = 0;
        for (int i = 1; i < codeArray.length; i++) {
            if (codeArray[i] > codeArray[greatestIndex]) {
                greatestIndex = i;
            }
        }
        if (greatestIndex == 0) {
            return "First digit";
        } else if (greatestIndex == 1) {
            return "Second digit";
        } else if (greatestIndex == 2) {
            return "Third digit";
        } else {
            return "No digit";
        }
    }
}

class Validator16 extends Validator {
    private final int secretCode;
    public Validator16(int secretCode) {
        super("Determines if there are more even or odd digits in the code");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        long evenCount = Arrays.stream(codeArray).filter(value -> value % 2 == 0).count();
        long oddCount = Arrays.stream(codeArray).filter(value -> value % 2 != 0).count();
        if (evenCount > oddCount) {
            return "Majority are even";
        } else {
            return "Majority are odd";
        }
    }
}

class Validator17 extends Validator {
    private final int secretCode;
    public Validator17(int secretCode) {
        super("Counts how many digits in the code are even");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        long count = Arrays.stream(codeArray).filter(value -> value % 2 == 0).count();
        if (count == 0) {
            return "None";
        } else if (count == 1) {
            return "One number";
        } else if (count == 2) {
            return "Two numbers";
        } else {
            return "Three numbers";
        }
    }
}

class Validator18 extends Validator {
    private final int secretCode;
    public Validator18(int secretCode) {
        super("Determines whether the sum of the digits of the code is even or odd");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        int sum = Arrays.stream(codeArray).sum();
        if (sum % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }
}

class Validator19 extends Validator {
    private final int secretCode;
    public Validator19(int secretCode) {
        super("Compares the sum of the first and the second digit of the code with the value 6");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int sum = getDigit(code, 0) + getDigit(code, 1);
        if (sum > 6) {
            return "Greater than 6";
        } else if (sum == 6) {
            return "Equal to 6";
        } else {
            return "Less than 6";
        }
    }
}

class Validator20 extends Validator {
    private final int secretCode;
    public Validator20(int secretCode) {
        super("Determines whether a digit in the code repeats, and if so, how many times");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);
        long count = Arrays.stream(codeArray).distinct().count();
        if (count == 3) {
            return "No duplicate";
        } else if (count == 2) {
            return "Duplicate";
        } else {
            return "Triplet";
        }
    }
}

class Validator21 extends Validator {
    private final int secretCode;
    public Validator21(int secretCode) {
        super("Determines if a code digit is found in exactly two copies in the code (but not three)");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);

        int[] countArray = new int[9];

        for (int num : codeArray) {
            countArray[num]++;
        }

        String ret = "False";
        for (int j : countArray) {
            if (j == 2) {
                ret = "True";
                break;
            }
        }
        return ret;
    }
}

class Validator22 extends Validator {
    private final int secretCode;
    public Validator22(int secretCode) {
        super("Determines whether the three digits of the code are in ascending or descending order");
        this.secretCode = secretCode;
    }

    @Override
    public boolean validate(int code) {
        String categorySecretCode = categorizeCode(secretCode);
        String categoryUserCode = categorizeCode(code);
        return Objects.equals(categoryUserCode, categorySecretCode);
    }

    @Override
    public String categorizeCode(int code) {
        int[] codeArray = convertToArray(code);

        boolean ascending = codeArray[0] <= codeArray[1] && codeArray[1] <= codeArray[2];
        boolean descending = codeArray[0] >= codeArray[1] && codeArray[1] >= codeArray[2];

        if (ascending) {
            return "Ascending";
        } else if (descending) {
            return "Descending";
        } else {
            return "None";
        }
    }
}