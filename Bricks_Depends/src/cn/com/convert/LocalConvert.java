/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.convert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public final class LocalConvert {

    private final static SimpleDateFormat _date_string_Converter = new SimpleDateFormat();
    private final static LocalConvert _CONVERTER = new LocalConvert();

    private LocalConvert() {
    }

    public static LocalConvert getInstance() {
        return _CONVERTER;
    }

    /**
     * 转换字符为日期格式
     * @param dateValue
     * @param format
     * @return
     */
    public Date convertStringToDate(String value, String partten) {


        _date_string_Converter.applyPattern(partten);
        try {
            return _date_string_Converter.parse((String) value);
        } catch (ParseException ex) {
            Logger.getLogger(LocalConvert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 转换日期为字符
     * @param dateValue
     * @param partten
     * @return
     */
    public String convertDateToString(Date value, String partten) {
        _date_string_Converter.applyPattern(partten);

        return _date_string_Converter.format(value);
    }

    /**
     * 转换int
     * @param value
     * @return
     */
    public int convertStringToInteger(String value) {
        return Integer.valueOf(value);
    }

    /**
     * 转换byte
     * @param value
     * @return
     */
    public byte convertStringToByte(String value) {
        return Byte.valueOf(value);
    }

    /**
     * 转换short
     * @param value
     * @return
     */
    public short convertStringToShort(String value) {
        return Short.valueOf(value);
    }

    /**
     * 转换long
     * @param value
     * @return
     */
    public long convertStringToLong(String value) {
        return Long.valueOf(value);
    }

    /**
     * 转换float
     * @param value
     * @return
     */
    public float convertStringToFloat(String value) {
        return Float.valueOf(value);
    }

    /**
     * 转换double
     * @param value
     * @return
     */
    public double convertStringToDouble(String value) {
        return Double.valueOf((String) value);
    }

    /**
     * 转换char
     * @param value
     * @return
     */
    public char convertStringToChar(String value) {
        return value.charAt(0);
    }

    /**
     * 转换bigdecimal
     * @param value
     * @return
     */
    public BigDecimal convertStringToBigDecimal(String value) {
        return new BigDecimal(value);
    }

    /**
     * 对象转换为目标类型
     * @param value
     * @param clazz
     * @param partten 日期格式转换 如果一般格式，可以传递null
     * @return
     */
    public Object convertStringToObject(String value, Class<?> clazz, String partten) {
        Object converted = null;
        if (clazz.equals(int.class) || clazz.equals(Integer.TYPE)) {
            converted = this.convertStringToInteger(value);
        } else if (clazz.equals(short.class) || clazz.equals(Short.TYPE)) {
            converted = this.convertStringToShort(value);
        } else if (clazz.equals(byte.class) || clazz.equals(Byte.TYPE)) {
            converted = this.convertStringToByte(value);
        } else if (clazz.equals(long.class) || clazz.equals(Long.TYPE)) {
            converted = this.convertStringToLong(value);
        } else if (clazz.equals(char.class) || clazz.equals(Character.TYPE)) {
            converted = this.convertStringToChar(value);
        } else if (clazz.equals(double.class) || clazz.equals(Double.TYPE)) {
            converted = this.convertStringToDouble(value);
        } else if (clazz.equals(BigDecimal.class)) {
            converted = this.convertStringToBigDecimal(value);
        } else if (clazz.equals(Date.class)) {
            converted = this.convertStringToDate(value, partten);
        } else {
            converted = value;
        }
        return converted;
    }
}
