package helper;

import http.AccessTokenRequestConfigure;
import http.UrlParam;
import org.springframework.stereotype.Component;
import xml.UnionPayRequestConfigure;
import xml.XmlElement;
import xml.XmlRootElement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WxHelper {

    private static final Map<Class<?>, Map<Field, String>> CONFIGURE_FIELD_URLPARAM_MAPPING = new HashMap<>();
    private static final Map<Class<?>, Map.Entry<RootTag, Map<Field, Object>>> CONFIGURE_FIELD_XML_MAPPING = new HashMap<>();
    private static final List<ValueHandler> xmlElementHandlers = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public Map<String, Object> requestAccessToken(AccessTokenRequestConfigure configure) throws IllegalAccessException, IOException {
//        String queryUrl = Configure.ACCESS_TOKEN_URL + buildUrlSearch(configure);
//        String result = HttpClient.get(queryUrl);
//        return objectMapper.readValue(result, HashMap.class);
        return null;
    }

    private static ValueHandler getValueHandler(Class clazz) {
        for (ValueHandler valueHandler: xmlElementHandlers) {
            if (valueHandler.support(clazz)) {
                return valueHandler;
            }
        }
        return null;
    }


    public String buildUrlSearch(Object configure) throws IllegalAccessException {
        if (configure == null) {
            throw new NullPointerException("AccessTokenRequestConfigure is null");
        }
        Map<Field, String> mapping = CONFIGURE_FIELD_URLPARAM_MAPPING.get(configure.getClass());
        if (mapping == null) {
            return "";
        }
        StringBuilder urlBuilder = new StringBuilder();
        for (Map.Entry<Field, String> entry: mapping.entrySet()) {
            Field f = entry.getKey();
            if (urlBuilder.length() == 0) {
                urlBuilder.append('?');
            }
            else {
                urlBuilder.append('&');
            }
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            String urlParam = entry.getValue();
            String paramValue = (String)f.get(configure);
            urlBuilder.append(urlParam).append("=").append(paramValue);
        }
        return urlBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    public String buildXml (Object configure, boolean isTop) throws IllegalAccessException {
        if (configure == null || configure.getClass() == null || CONFIGURE_FIELD_XML_MAPPING.get(configure.getClass()) == null) {
            return "";
        }
        Map.Entry<RootTag, Map<Field, Object>> xmlFieldMapping = CONFIGURE_FIELD_XML_MAPPING.get(configure.getClass());
        RootTag rootTag = xmlFieldMapping.getKey();

        StringBuilder sb = new StringBuilder();
        if (isTop) {
            sb.append(rootTag.getNamespace());
            appendTagBegin(sb, rootTag.getTagName());
        }
        Map<Field, Object> fieldMapping = xmlFieldMapping.getValue();
        for (Map.Entry<Field, Object> entry: fieldMapping.entrySet()) {
            Field f = entry.getKey();
            Object value = entry.getValue();
            Entry<String, Boolean> tagEntry = (Entry<String, Boolean>)value;
            //tag head
            appendTagBegin(sb, tagEntry.getKey());
            ValueHandler valueHandler = getValueHandler(f.getType());
            //complex type
            if (valueHandler == null) {
                Object tagValue = f.get(configure);
                String innerFieldXml = buildXml(tagValue, false);
                sb.append(innerFieldXml);
            }
            //simple type
            else {
                Object tagValue = f.get(configure);
                //need to escape
                if (tagEntry.getValue()) {
                    escapeTagValue(sb, tagEntry.getKey());
                }
                else {
                    sb.append(valueHandler.handle(tagValue));
                }
            }
            //tag tail
            appendTagEnd(sb, tagEntry.getKey());
        }
        if (isTop) {
            appendTagEnd(sb, rootTag.getTagName());
        }
        return sb.toString();
    }

    private WxHelper appendTagBegin(StringBuilder sb, String tagName) {
        sb.append('<').append(tagName).append('>');
        return this;
    }
    private WxHelper appendTagEnd(StringBuilder sb,String tagName) {
        sb.append("</").append(tagName).append('>');
        return this;
    }
    private WxHelper escapeTagValue(StringBuilder sb,String tagValue) {
        sb.append("<![CDATA[").append(tagValue).append("]]>");
        return this;
    }

    static {
        xmlElementHandlers.add(new StringValueHandler());
    }

    //load UrlParam class
    static {
        Map<Field, String> accessTokenRequestConfigureClassMap = new HashMap<>();
        Class<AccessTokenRequestConfigure> accessTokenRequestConfigureClass =  AccessTokenRequestConfigure.class;
        Field[] accessTokenRequestConfigureClassFields = accessTokenRequestConfigureClass.getDeclaredFields();
        for (Field f: accessTokenRequestConfigureClassFields) {
            String urlParamName;
            UrlParam urlParam = f.getAnnotation(UrlParam.class);
            if (urlParam != null) {
                urlParamName = urlParam.value();
                if (urlParamName == null || urlParamName.trim().length() == 0) {
                    urlParamName = f.getName();
                }
            }
            else {
                urlParamName = f.getName();
            }
            accessTokenRequestConfigureClassMap.put(f, urlParamName);
        }
        CONFIGURE_FIELD_URLPARAM_MAPPING.put(accessTokenRequestConfigureClass, accessTokenRequestConfigureClassMap);
    }
    //load xml config class
    static {
        analyseXmlConfigMapping(UnionPayRequestConfigure.class);
    }

    private static void analyseXmlConfigMapping(Class<?> clazz) {
        if (clazz == null || CONFIGURE_FIELD_XML_MAPPING.containsKey(clazz)) {
            return;
        }
        XmlRootElement xmlRootElement = clazz.getAnnotation(XmlRootElement.class);
        String rootTag;
        String namespace = xmlRootElement.namespace();
        if (xmlRootElement == null) {
            rootTag = clazz.getSimpleName();
        }
        else {
            rootTag = xmlRootElement.value();
        }
        Entry<RootTag, Map<Field, Object>> xmlConfigureMapping = new Entry<>(new RootTag(rootTag, namespace));
        CONFIGURE_FIELD_XML_MAPPING.put(clazz, xmlConfigureMapping);
        Map<Field, Object> xmlFiledsConfigureMapping = new HashMap();
        xmlConfigureMapping.setValue(xmlFiledsConfigureMapping);
        Field[] unionPayRequestConfigureClassFields = clazz.getDeclaredFields();
        for (Field f: unionPayRequestConfigureClassFields) {
            //非静态变量
            if (!Modifier.isStatic(f.getModifiers())) {
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                Class<?> fieldType = f.getType();
                XmlRootElement innerXmlRootElement = fieldType.getAnnotation(XmlRootElement.class);
                XmlElement e = f.getAnnotation(XmlElement.class);
                String tagName;
                boolean escape;
                if (innerXmlRootElement != null) {
                    if (!CONFIGURE_FIELD_XML_MAPPING.containsKey(fieldType)) {
                        analyseXmlConfigMapping(fieldType);
                    }
                    if (e != null) {
                        tagName = e.value();
                    }
                    else if (innerXmlRootElement.value() != null && innerXmlRootElement.value().trim().length() > 0) {
                        tagName = innerXmlRootElement.value();
                    }
                    else {
                        tagName = f.getName();
                    }
                    escape = false;
                }
                else {
                    //非静态变量
                    if (e != null) {
                        tagName = e.value();
                    }
                    else {
                        tagName = f.getName();
                    }
                    escape = e.escape();
                }
                xmlFiledsConfigureMapping.put(f, new Entry<String, Boolean>(tagName, escape));
            }
        }
    }

    public interface ValueHandler {
        boolean support(Class<?> clazz);
        String handle(Object t);
    }
    public static abstract class AbstractValueHandler implements ValueHandler {

        @Override
        public boolean support(Class<?> clazz) {
            return t == clazz;
        }

        private final Class<?> t;
        public AbstractValueHandler(Class<?> t) {
            this.t = t;
        }
    }
    public static class StringValueHandler extends AbstractValueHandler {
        @Override
        public String handle(Object s) {
            return (String) s;
        }

        public StringValueHandler() {
            super(String.class);
        }
    }

    private static class RootTag {
        private String tagName;
        private String namespace;

        public String getTagName() {
            return tagName;
        }

        public RootTag setTagName(String tagName) {
            this.tagName = tagName;
            return this;
        }

        public String getNamespace() {
            return namespace;
        }

        public RootTag setNamespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public RootTag(String tagName, String namespace) {
            this.tagName = tagName;
            this.namespace = namespace;
        }
    }

    private static class Entry<K, V> implements HashMap.Entry<K, V> {

        private K k;

        private V v;

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }

        public Entry(K k) {
            this.k = k;
        }

        public Entry(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

}

