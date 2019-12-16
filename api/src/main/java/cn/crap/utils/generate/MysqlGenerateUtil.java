package cn.crap.utils.generate;

/**
 * @author Ehsan
 * @date 2018/12/31 15:43
 */

import cn.crap.enu.GenerateType;
import cn.crap.utils.Tools;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;

@Service
public class MysqlGenerateUtil extends BaseGenerateUtil {
    //表头
    private static String PO_FIELD = "CA_PO_FIELD";

    //DEFAULT NOT
    private static String FIELD = "\n\t`%s` %s CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci %s NULL COMMENT '%s',";
    @Override
    public boolean canExecute(String type){
        if (GenerateType.MYSQL.name().equals(type)) {
            return true;
        }
        return false;
    }

    /**
     * 数据库表
     * @return
     */
    @Override
    public String execute(String fields) throws Exception{
        Set<String> fieldSet = getFields(fields);
        if(fieldSet.size() == 0){
            return "字段不能为空，请选择字段";
        }
        StringBuilder poField = new StringBuilder();
        StringBuilder poGetSet = new StringBuilder();

        for (String field : fieldSet){
            String[] fieldSplits = field.split("_CA_SEPARATOR_");
            String type = fieldSplits.length > 1 ? fieldSplits[1]: null;
            field = fieldSplits[0];
            poField.append(String.format(FIELD, field,type, "DEFAULT","注释"));
//            poGetSet.append(String.format(FIELD, sqlType2JavaType(type), upperCaseFirst(getCamel(field)), getCamel(field),
//                    upperCaseFirst(getCamel(field)), sqlType2JavaType(type), getCamel(field), getCamel(field), getCamel(field)));

        }
        String poFilUrl = Tools.getServicePath() + "WEB-INF/classes/generate/MySql.txt";
        String poContent = Tools.readFile(poFilUrl);


        poContent = poContent.replaceAll(PO_FIELD, poField.toString());
//        poContent = poContent.replaceAll(PO_GET_SET, poGetSet.toString());

        return poContent;
    }
}
