package laustrup.utilities;

import laustrup.utilities.tests.*;

import laustrup.utilities.tests.items.TestItems;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilitiesApplicationTests {

    private PrinterTests _printerTests = new PrinterTests();
    private final LisztTeszts _lisztTeszts = new LisztTeszts();
    private SesztTeszts _sesztTeszts = new SesztTeszts();

    @Test
    void contextLoads() {
        new TestItems().itemTest();
        printerTests();
        lisztTeszts();
        sesztTeszts();
    }

    private void printerTests() {
        String[][] inputs = {
            {"null", "empty", "This is some content", "multiple", "border check"},
            {"NumberFormatException"}
        };
        for (String content : inputs[0])
            _printerTests.canPrint(content);
        for (String content : inputs[0])
            for (String exception : inputs[1])
                _printerTests.canPrint(content,exception);

        long[] measurements = {
            -5,0,1,5,1000,1001,1005,2001,2000,2005,
            60000,61000,61001,61005,62000,62005,
            122000,122001,121005,122005,3600000,7200000,
            3661000,3662000,3661001,3661005,3662005,3722000,3721001,3721005,3722005,
            7261000,7262000,7261001,7261005,7262005,7322000,7321001,7321005,7322005
        };
        for (long measurement : measurements)
            _printerTests.canMeasure(measurement);
    }

    private void lisztTeszts() {
        _lisztTeszts.constructorTest(true);
        _lisztTeszts.constructorTest(false);
        _lisztTeszts.canAddSingleElement();
        _lisztTeszts.canAddAnArray(true);
        _lisztTeszts.canAddAnArray(false);
        _lisztTeszts.canSet();

        int[][] setMultipleInputs = {{0,0},{1,0},{0,1},{3,0},{0,3},{1,1},{3,1},{1,3},{3,3}};
        for (int[] inputs : setMultipleInputs)
            _lisztTeszts.canSetMultiple(inputs[0], inputs[1]);

        _lisztTeszts.canRemove();
        _lisztTeszts.canRemoveMultiple();
    }

    private void sesztTeszts() {
        _sesztTeszts.canContain();
        _sesztTeszts.canContainMultiple();
        _sesztTeszts.canInitiateWithInputs();
        _sesztTeszts.canAdd();
        _sesztTeszts.canAddUniques();
        _sesztTeszts.canGet();
        _sesztTeszts.canGetByKey();
        _sesztTeszts.canGetByIndex();
        _sesztTeszts.canRemove();
        _sesztTeszts.canRemoveMultiple();
        _sesztTeszts.canRetain();
        _sesztTeszts.canSet();

        int[][] setMultipleInputs = {{1,2},{2,1},{-1,-1},{-1,1},{1,-1}};
        for (int[] inputs : setMultipleInputs)
            _sesztTeszts.canSetMultiple(inputs[0], inputs[1]);
    }
}
