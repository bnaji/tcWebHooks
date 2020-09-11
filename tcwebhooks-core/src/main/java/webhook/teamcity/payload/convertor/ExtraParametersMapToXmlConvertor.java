package webhook.teamcity.payload.convertor;

import java.util.Map.Entry;
import java.util.Objects;

import webhook.teamcity.payload.content.ExtraParameters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ExtraParametersMapToXmlConvertor implements Converter {

    public void marshal(Object myMap, HierarchicalStreamWriter writer,
            MarshallingContext context) {
    	ExtraParameters map = (ExtraParameters) myMap;
    	for (Entry<String, String> entry : map.getEntriesAsSet()){
    		if (Objects.nonNull(entry.getValue())) {
				this.addNode(writer, entry.getKey().trim(), entry.getValue().toString().trim());
			}
    	}
    }
    
    private void addNode(HierarchicalStreamWriter writer, String name, String value){
        writer.startNode(name);
        writer.setValue(value);
        writer.endNode();	
    }
    
    /* 
     * We don't support unmarshalling. One way only I'm afraid.
     * (non-Javadoc)
     * @see com.thoughtworks.xstream.converters.Converter#unmarshal(com.thoughtworks.xstream.io.HierarchicalStreamReader, com.thoughtworks.xstream.converters.UnmarshallingContext)
     */
	public Object unmarshal(HierarchicalStreamReader arg0,
			UnmarshallingContext arg1) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class clazz) {
		return ExtraParameters.class == clazz;
	}
}
