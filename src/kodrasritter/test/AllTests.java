package kodrasritter.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Ausfuehren aller Testfaelle
 * 
 * @author Mathias Ritter
 * @author Dominik Kodras
 */
@RunWith(Suite.class)
@SuiteClasses({ TestGUI.class, TestNetwork.class, TestMain.class, TestMessage.class })
public class AllTests {

}
