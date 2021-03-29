package nablarch.test.core.reader;

import nablarch.test.core.messaging.MessagePool;
import nablarch.test.core.util.interpreter.BinaryFileInterpreter;
import nablarch.test.core.util.interpreter.TestDataInterpreter;

import java.util.ArrayList;
import java.util.List;

public class CustomBasicTestDataParser extends BasicTestDataParser {

    private TestDataReader testDataReader;

    private List<TestDataInterpreter> interpreters;    
 
    public MessagePool getMessageWithoutCache(String path, String resourceName, DataType dataType, String id) {
        CustomSendSyncMessageParser agent = new CustomSendSyncMessageParser(testDataReader, addBinaryFileInterpreter(path), dataType);
        agent.parse(path, resourceName, id, false);
        return agent.getResult();
    }

    public void setTestDataReader(TestDataReader testDataReader) {
        super.setTestDataReader(testDataReader);
        this.testDataReader = testDataReader;
    }

    public void setInterpreters(List<TestDataInterpreter> interpretersPrototype) {
        super.setInterpreters(interpretersPrototype);
        this.interpreters = interpretersPrototype;
    }
    
    private List<TestDataInterpreter> addBinaryFileInterpreter(String path) {
        BinaryFileInterpreter fileInterpreter = new BinaryFileInterpreter(path);
        List<TestDataInterpreter> newInterpreters = new ArrayList<TestDataInterpreter>(
                interpreters.size() + 1);
        newInterpreters.add(fileInterpreter);
        newInterpreters.addAll(interpreters);
        return newInterpreters;
    }
}
