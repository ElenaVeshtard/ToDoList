package com.example.test.data

import com.example.test.domain.CheckListModel

class CheckListRemoteDataSourceFake : CheckListRemoteDataSource {

    override suspend fun getItemCheckList(): List<CheckListModel> {
        return arrayListOf(
            CheckListModel(
                "https://thumbs.dreamstime.com/z/%D0%BA%D0%B0%D1%80%" +
                        "D0%B8%D0%BA%D0%B0%D1%82%D1%83%D1%80%D0%B0-%D0%BF%D0%BB%D0%B0%D0%B2%D0%B0%D0%BD%D0%B8%D1%" +
                        "8F-%D0%B2-%D0%B1%D0%B0%D1%81%D1%81%D0%B5%D0%B9%D0%BD%D0%B5-%D0%BC%D0%B0%" +
                        "D0%BB%D1%8C%D1%87%D0%B8%D0%BA%D0%B0-%D0%B8%D0%BB%D0%BB%D1%8E%D1%81%D1%82%D1%80%D0%B0%D1%86" +
                        "%D0%B8%D0%B8-%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%B0-206484769.jpg",
                "Бассейн",
                "вторник, четверг 18.30",
                "  Close  "
            ),
            CheckListModel(
                "https://img.freepik.com/free-vector/cheerful-little-boy-pulling-" +
                        "himself-up-on-a-sport-horizontal-bar-in-a-gym_376504-1327.jpg",
                "Гимнастика",
                "понедельник, среда 14.00",
                "  Close  "
            ),
            CheckListModel(
                "https://img.freepik.com/free-vector/little-kid-riding-roller-skates_29937-3932.jpg",
                "Ролики",
                "четверг, воскресеньк 20.00",
                "  Close  "
            ),
            CheckListModel(
                "https://thumbs.dreamstime.com/b/%D1%81%D1%87%D0%B0%D1%81%D1%82%D0%BB%" +
                        "D0%B8%D0%B2%D1%8B%D0%B9-%D0%BC%D0%B8%D0%BB%D1%8B%D0%B9-%D0%BC%D0%B0%D0%" +
                        "BB%D1%8C%D1%87%D0%B8%D0%BA-%D1%82%D0%B0%D1%89%D0%B8-%D1%81%D1%83%D0%BC%D0%" +
                        "BA%D1%83-%D1%82%D1%8F%D0%BD%D0%B5%D1%82-%D1%81%D1%83%D0%BC%D0%BA%D0%B8-%D0" +
                        "%BF%D1%83%D1%82%D0%B5%D1%88%D0%B5%D1%81%D1%82" +
                        "%D0%B2%D0%BE%D0%B2%D0%B0%D1%82%D1%8C-%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80-164179184.jpg",
                "Поездка",
                "1-2 дня",
                "  Close  "
            )
        )
    }
}