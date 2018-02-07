package com.example.demo.services.impl;

import com.example.demo.dto.datatables.DataTable;
import com.example.demo.dto.datatables.oAria;
import com.example.demo.dto.datatables.oPaginate;
import com.example.demo.services.DataTableService;
import org.springframework.stereotype.Service;

@Service
public class DataTableServiceImpl implements DataTableService {
    @Override
    public DataTable get(String language) {
        DataTable dataTableDto = new DataTable();
        oAria oAria = new oAria();
        oPaginate oPaginate = new oPaginate();
        switch (language){
            case "en":
                oAria.setsSortAscending(": activate to sort column ascending");
                oAria.setsSortDescending(": activate to sort column descending");
                oPaginate.setsFirst("First");
                oPaginate.setsLast("Last");
                oPaginate.setsNext("Next");
                oPaginate.setsPrevious("Previous");
                dataTableDto.setsEmptyTable("No data available in table");
                dataTableDto.setsInfo("Showing _START_ to _END_ of _TOTAL_ entries");
                dataTableDto.setsInfoEmpty("Showing 0 to 0 of 0 entries");
                dataTableDto.setsInfoFiltered("(filtered from _MAX_ total entries)");
                dataTableDto.setsInfoPostFix("");
                dataTableDto.setsInfoThousands(",");
                dataTableDto.setsLengthMenu("Show _MENU_ entries");
                dataTableDto.setsLoadingRecords("Loading...");
                dataTableDto.setsProcessing("Processing...");
                dataTableDto.setsSearch("Search:");
                dataTableDto.setsZeroRecords("No matching records found");
                dataTableDto.setoPaginate(oPaginate);
                dataTableDto.setoAria(oAria);
                break;
            case "uz":
                oAria.setsSortAscending(": to'g'ri tartiblash");
                oAria.setsSortDescending(": teskari tartiblash");
                oPaginate.setsFirst("Birinchi");
                oPaginate.setsLast("Son'ggi");
                oPaginate.setsNext("Keyingi");
                oPaginate.setsPrevious("Avvalgi");
                dataTableDto.setsEmptyTable("Ma'lumot yo'q");
                dataTableDto.setsInfo("Umumiy _TOTAL_ yozuvlarlardan _START_ dan _END_ gachasi ko'rsatilmoqda");
                dataTableDto.setsInfoEmpty("Umumiy 0 yozuvlardan 0 dan 0 gachasi ko'rsatilmoqda");
                dataTableDto.setsInfoFiltered("(_MAX_ yozuvlardan filtrlandi)");
                dataTableDto.setsInfoPostFix("");
                dataTableDto.setsInfoThousands(",");
                dataTableDto.setsLengthMenu("Bir sahifada _MENU_ ta element");
                dataTableDto.setsLoadingRecords("Yozuvlar yuklanmoqda....");
                dataTableDto.setsProcessing("Ishlayapman...");
                dataTableDto.setsSearch("Izlash:");
                dataTableDto.setsZeroRecords("Ma'lumot yo'q.");
                dataTableDto.setoPaginate(oPaginate);
                dataTableDto.setoAria(oAria);
                break;
            case "ru":
                oAria.setsSortAscending(": \u0430\u043A\u0442\u0438\u0432\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u0434\u043B\u044F \u0441\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u043A\u0438 \u0441\u0442\u043E\u043B\u0431\u0446\u0430 \u043F\u043E \u0432\u043E\u0437\u0440\u0430\u0441\u0442\u0430\u043D\u0438\u044E");
                oAria.setsSortDescending(": \u0430\u043A\u0442\u0438\u0432\u0438\u0440\u043E\u0432\u0430\u0442\u044C \u0434\u043B\u044F \u0441\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u043A\u0438 \u0441\u0442\u043E\u043B\u0431\u0446\u0430 \u043F\u043E \u0443\u0431\u044B\u0432\u0430\u043D\u0438\u044E");
                oPaginate.setsFirst("\u041F\u0435\u0440\u0432\u0430\u044F");
                oPaginate.setsLast("\u041F\u043E\u0441\u043B\u0435\u0434\u043D\u044F\u044F");
                oPaginate.setsNext("\u0421\u043B\u0435\u0434\u0443\u044E\u0449\u0430\u044F");
                oPaginate.setsPrevious("\u041F\u0440\u0435\u0434\u044B\u0434\u0443\u0449\u0430\u044F");
                dataTableDto.setsEmptyTable("\u0412 \u0442\u0430\u0431\u043B\u0438\u0446\u0435 \u043E\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u044E\u0442 \u0434\u0430\u043D\u043D\u044B\u0435");
                dataTableDto.setsInfo("\u0417\u0430\u043F\u0438\u0441\u0438 \u0441 _START_ \u0434\u043E _END_ \u0438\u0437 _TOTAL_ \u0437\u0430\u043F\u0438\u0441\u0435\u0439");
                dataTableDto.setsInfoEmpty("\u0417\u0430\u043F\u0438\u0441\u0438 \u0441 0 \u0434\u043E 0 \u0438\u0437 0 \u0437\u0430\u043F\u0438\u0441\u0435\u0439");
                dataTableDto.setsInfoFiltered("(\u043E\u0442\u0444\u0438\u043B\u044C\u0442\u0440\u043E\u0432\u0430\u043D\u043E \u0438\u0437 _MAX_ \u0437\u0430\u043F\u0438\u0441\u0435\u0439)");
                dataTableDto.setsInfoPostFix("");
                dataTableDto.setsInfoThousands(",");
                dataTableDto.setsLengthMenu("\u041F\u043E\u043A\u0430\u0437\u0430\u0442\u044C _MENU_ \u0437\u0430\u043F\u0438\u0441\u0435\u0439");
                dataTableDto.setsLoadingRecords("\u0417\u0430\u0433\u0440\u0443\u0437\u043A\u0430 \u0437\u0430\u043F\u0438\u0441\u0435\u0439...");
                dataTableDto.setsProcessing("\u041F\u043E\u0434\u043E\u0436\u0434\u0438\u0442\u0435...");
                dataTableDto.setsSearch("\u041F\u043E\u0438\u0441\u043A:");
                dataTableDto.setsZeroRecords("\u0417\u0430\u043F\u0438\u0441\u0438 \u043E\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u044E\u0442.");
                dataTableDto.setoPaginate(oPaginate);
                dataTableDto.setoAria(oAria);
                break;
            default:
                break;
        }
        return dataTableDto;
    }
}
