package atest.jmock.builder;

import org.jmock.builder.Mock;
import org.jmock.builder.MockObjectTestCase;
import org.jmock.dynamic.DynamicMockError;


public class ExpectNotCalledAcceptanceTest 
    extends MockObjectTestCase 
{
    private static interface MockedInterface {
        public void method();
    }
    
    public void testExpectNotCalledOverridesStubAndFailsIfCalled() {
        Mock mock = new Mock(MockedInterface.class,"mock");
        
        mock.method("method").noParams().isVoid();
        mock.method("method").noParams().isVoid().expectNotCalled();
        
        try {
            ((MockedInterface)mock.proxy()).method();
        }
        catch( DynamicMockError error ) {
            // expected
        }
    }
    
    public void testExpectNotCalledVerifiesIfNotCalled() {
        Mock mock = new Mock(MockedInterface.class,"mock");
        
        mock.method("method").noParams().isVoid();
        mock.method("method").noParams().isVoid().expectNotCalled();
        
        mock.verify();
    }
}
