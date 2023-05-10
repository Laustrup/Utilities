package laustrup.utilities.console;

import laustrup.quality_assurance.Tester;

public abstract class PrinterTester extends Tester<String> {

    /** The Printer that is used for this test */
    protected static final Printer _printer = Printer.get_instance();

    /** Defines a content input that will be used in parametrized tests. */
    protected final String _null = "null", _empty = "empty", _single = "This is some content",
            _multiple = "multiple", _borderCheck = "border check";

    /** Defines an exception inputs that will be used in parametrized tests. */
    protected final String _numberFormatException = "NumberFormatException";
}
