package sn.ssi.sigmap.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sn.ssi.sigmap.domain.TableDeTransaction;
import sn.ssi.sigmap.domain.TableRow;
import sn.ssi.sigmap.domain.enumeration.DataType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableService {

    public List<TableDeTransaction> jsonUnwrapper(List<Map<String, Object>> params)
        throws ParseException, NumberFormatException {
        List<TableDeTransaction> json = new ArrayList<>();
        for (Map<String, Object> param : params) {
//            String column = param.get("columnName").toString();
            DataType type = DataType.valueOf(param.get("dataType").toString());
            TableDeTransaction row = new TableDeTransaction();
//            row.setColumnName(column);
            row.setDataType(type);
            boolean value = StringUtils.isNotEmpty(param.get("value").toString());
            if (value) {
                switch (row.getDataType()) {
                    case INT:
                        row.setIntValue(Integer.valueOf(param.get("value").toString()));
                        break;
                    case DATE:
//                        java.util.Date dateUtil = DateUtils.parseDate(
//                            param.get("value").toString(),
//                            "yyyy-MM-dd",
//                            "dd/MM/yyyy"
//                        );
                        row.setDateValue(LocalDate.parse(param.get("value").toString()));
                        break;
                    case DATE_TIME:
//                        java.util.Date dateTimeUtil = DateUtils.parseDate(param.get("value").toString(),
//                            "yyyy-MM-dd HH:mm:ss",
//                            "yyyy-MM-dd'T'HH:mm:ss");
                        row.setZoneLocalTimeValue(ZonedDateTime.parse(param.get("value").toString()));
                        break;
                    case TEXT:
                        row.setTextValue(param.get("value").toString());
                        break;
                    case BOOLEAN:
                        row.setBooleanValue(Boolean.valueOf(param.get("value").toString()));
                        break;
                    case MONEY:
                        row.setMoneyValue(new BigDecimal(param.get("value").toString()));
                        break;
                    default:
//                        row.setStringValue(param.get("value").toString());
                }
            }
            json.add(row);
        }
        return json;
    }

    public List<Map<String, Object>> jsonWrapper(List<TableDeTransaction> rows) {
        List<Map<String, Object>> json = new ArrayList<>();
        for (TableDeTransaction row : rows) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", row.getId());
            obj.put("masterId", row.getMasterId());
            obj.put("dataType", row.getDataType());
            obj.put("columnName", row.getColumnName());
            switch (row.getDataType()) {
                case INT:
                    obj.put("value", row.getIntValue());
                    break;
                case DATE:
                    obj.put("value", row.getDateValue());
                    break;
                case DATE_TIME:
                    obj.put("value", row.getZoneLocalTimeValue());
                    break;
                case TEXT:
                    obj.put("value", row.getTextValue());
                    break;
                case BOOLEAN:
                    obj.put("value", row.isBooleanValue());
                    break;
                case MONEY:
                    obj.put("value", row.getMoneyValue());
                    break;
                default:
//                    obj.put("value", row.getStringValue());
            }
            json.add(obj);
        }
        return json;
    }

    public List<Map<String, Object>> rowJsonWrapper(List<TableRow> rows) {
        List<Map<String, Object>> json = new ArrayList<>();
        for (TableRow row : rows) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", "");
            obj.put("columnName", row.getColumnName());
            obj.put("dataType", row.getDataType());
            obj.put("labelName", row.getLabelName());
            obj.put("value", "");
            json.add(obj);
        }
        return json;
    }
}
