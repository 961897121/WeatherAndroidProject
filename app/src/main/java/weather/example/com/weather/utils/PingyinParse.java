package weather.example.com.weather.utils;

/**
 * Created by krito on 2019/9/11.
 */

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文转拼音
 */
public class PingyinParse {

    public String toPinYing(String str){
        //汉字的字符数组
        char[] hz = str.toCharArray();
        //拼音数组
        String[] py = new String[hz.length];

        //设置拼音输出格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType( HanyuPinyinCaseType.LOWERCASE );
        format.setToneType( HanyuPinyinToneType.WITHOUT_TONE );//没音符
        format.setVCharType( HanyuPinyinVCharType.WITH_V );

        String pys = "";//存放字符串
        int len = hz.length;//字符数组长度

        try {
            for (int i = 0 ; i < len ; i++){
                ///判断是否汉字
                if (Character.toString( hz[i] ).matches( "[\\u4E00-\\u9FA5]+")){
                    //放入拼音数组
                    py = PinyinHelper.toHanyuPinyinStringArray( hz[i] , format );
                    //取出汉字全拼的,存放字符串
                    pys = pys + py[0];
                }else {
                    //不是汉字
                    pys = pys + Character.toString( hz[i] );
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return pys;
    }
}
