package cn.com.byd.compose.beans;

import cn.com.byd.compose.scope.MethodContext;
import cn.com.byd.compose.utils.TypeConversion;
import cn.com.byd.exceptions.ParameterException;
import cn.com.byd.utils.StringUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parameter implements Serializable {

    /**
     * 参数列表
     */
    private List<ParameterType> parameters = null;
    
    public void setParameters(List<ParameterType> parameters) {
        this.parameters = parameters;
    }
    
    public List<ParameterType> getParameters() {
        return parameters;
    }

    /**
     * 获取参数的实际对象
     * @param methodContext
     * @return
     * @throws ParameterException
     */
    public Object[] getParametersValue(MethodContext methodContext) throws ParameterException {
        Object[] args = new Object[]{};
        if (parameters == null || parameters.isEmpty()) {
            return args;
        }
        args = new Object[parameters.size()];
        Object obj = null;
        int idx = 0;
        for (ParameterType paraType : parameters) {
            if (!StringUtil.isEmptyAndNull(paraType.getReferenceValue())) {
                obj = methodContext.getIdValue(paraType.getReferenceValue());
                if (!paraType.getClazz().equals(obj.getClass())) {
                    throw new ParameterException("parameter type not matching.args[" + idx + "],expect " + paraType.getClazz());
                }
                args[idx++] = obj;
            } else if (paraType.getBeanFieldMap() != null && !paraType.getBeanFieldMap().isEmpty()) {
                Map<String, Object> mapValue = new HashMap<String, Object>();
                for (String key : paraType.getBeanFieldMap().keySet()) {
                    if (paraType.getBeanFieldTypeMap().containsKey(key)) {
                        mapValue.put(key, TypeConversion.converObject(paraType.getBeanFieldTypeMap().get(key), methodContext.getIdValue(paraType.getBeanFieldMap().get(key))));
                    } else {
                        mapValue.put(key, methodContext.getIdValue(paraType.getBeanFieldMap().get(key)));
                    }
                }
                obj = TypeConversion.converCreateBean(paraType.getTypeName(), mapValue);
                args[idx++] = obj;
            } else {
                args[idx++] = obj;
            }
        }
        return args;
    }

    /**
     *验证参数个数及类型是否一致
     * @param arges
     * @throws ParameterException
     */
    public void checkParameters(Object... arges) throws ParameterException {
        if (arges.length != parameters.size()) {
            throw new ParameterException("parameter not matching.");
        }
        int idx = 0;
        // 验证参数类型是否一致
        for (Object obj : arges) {
            if (!obj.getClass().equals(parameters.get(idx).getClazz())) {
                throw new ParameterException("parameter[" + idx + "] type not matching.");
            }
            idx++;
        }
    }
}
