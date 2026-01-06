package view

object OutputView {
    // 0,1,2,3,4,5,6
    val week = listOf("월", "화", "수", "목", "금", "토", "일")
    val PublicHoliday = listOf<Pair<Int, Int>>(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 5),
        Pair(6, 6),
        Pair(8, 15),
        Pair(10, 3),
        Pair(10, 9),
        Pair(12, 25)
    )

    data class Datas(
        val month: Int, // 월
        val day: Int, // 일
        val dow: String, // 요일
        var name: String // 사람이름
    )

    fun printEmergenyTimeTable(
        month: Int,
        startDayOfTheWeek: String,
        weekDayStaffList: MutableList<String>,
        weekEndStaffList: MutableList<String>
    ) {
        var indexOfWeekDay = 0
        var indexOfWeekEnd = 0
        val indexStartDayOfTheWeek = week.indexOf(startDayOfTheWeek) //요일의 인덱스 순서 숫자로 받아서(수=2)
        // 시작 요일부터 week 순환(바뀌어야 하는건 요일 뿐)

        var index = indexStartDayOfTheWeek
        val dataList = mutableListOf<Datas>() // 결과를 출력할 리스트 생성
        when (month) {
            // 31일까지 있는 달
            1, 3, 5, 7, 8, 10, 12 ->
                // 31일까지
                for (day in 1..31) {
                    val dow = week[index] // 오늘의 요일
                    val isWeekend = (dow == "토" || dow == "일") // 주말 조건
                    val isHoliday = PublicHoliday.contains(Pair(month, day)) // 공휴일 조건
                    // 평일인데
                    if (!isWeekend) {
                        // 공휴일이면
                        if (isHoliday) {
                            dataList.add(
                                Datas(
                                    month,
                                    day,
                                    dow + "(휴일)",
                                    weekEndStaffList[indexOfWeekEnd]
                                )
                            )
                            indexOfWeekEnd++
                        }
                        // 공휴일이 아니면
                        if (!isHoliday) {
                            dataList.add(Datas(month, day, dow, weekDayStaffList[indexOfWeekDay]))
                            indexOfWeekDay++
                        }
                    } else {
                        // 토,일이면 그냥 공휴일이든 말든
                        dataList.add(Datas(month, day, dow, weekEndStaffList[indexOfWeekEnd]))
                        indexOfWeekEnd++
                    }
                    // 요일 인덱스는 0~6에 머물도록
                    index = (index + 1) % 7
                    // 한바퀴 돌았으면 다시 돌아
                    indexOfWeekDay %= (weekDayStaffList.size)
                    indexOfWeekEnd %= (weekEndStaffList.size)
                }
            // 30일까지 있는 달
            4, 6, 9, 11 ->
                for (day in 1..30) {
                    val dow = week[index] // 오늘의 요일
                    val isWeekend = (dow == "토" || dow == "일") // 주말 조건
                    val isHoliday = PublicHoliday.contains(Pair(month, day)) // 공휴일 조건
                    // 평일인데
                    if (!isWeekend) {
                        // 공휴일이면
                        if (isHoliday) {
                            dataList.add(
                                Datas(
                                    month,
                                    day,
                                    dow + "(휴일)",
                                    weekEndStaffList[indexOfWeekEnd]
                                )
                            )
                            indexOfWeekEnd++
                        }
                        // 공휴일이 아니면
                        if (!isHoliday) {
                            dataList.add(Datas(month, day, dow, weekDayStaffList[indexOfWeekDay]))
                            indexOfWeekDay++
                        }
                    } else {
                        // 토,일이면 그냥 공휴일이든 말든
                        dataList.add(Datas(month, day, dow, weekEndStaffList[indexOfWeekEnd]))
                        indexOfWeekEnd++
                    }
                    // 요일 인덱스는 0~6에 머물도록
                    index = (index + 1) % 7
                    // 한바퀴 돌았으면 다시 돌아
                    indexOfWeekDay %= (weekDayStaffList.size)
                    indexOfWeekEnd %= (weekEndStaffList.size)
                }
            // 28일까지 있는 달
            2 ->
                for (day in 1..28) {
                    val dow = week[index] // 오늘의 요일
                    val isWeekend = (dow == "토" || dow == "일") // 주말 조건
                    val isHoliday = PublicHoliday.contains(Pair(month, day)) // 공휴일 조건
                    // 평일인데
                    if (!isWeekend) {
                        // 공휴일이면
                        if (isHoliday) {
                            dataList.add(
                                Datas(
                                    month,
                                    day,
                                    dow + "(휴일)",
                                    weekEndStaffList[indexOfWeekEnd]
                                )
                            )
                            indexOfWeekEnd++
                        }
                        // 공휴일이 아니면
                        if (!isHoliday) {
                            dataList.add(Datas(month, day, dow, weekDayStaffList[indexOfWeekDay]))
                            indexOfWeekDay++
                        }
                    } else {
                        // 토,일이면 그냥 공휴일이든 말든
                        dataList.add(Datas(month, day, dow, weekEndStaffList[indexOfWeekEnd]))
                        indexOfWeekEnd++
                    }
                    // 요일 인덱스는 0~6에 머물도록
                    index = (index + 1) % 7
                    // 한바퀴 돌았으면 다시 돌아
                    indexOfWeekDay %= (weekDayStaffList.size)
                    indexOfWeekEnd %= (weekEndStaffList.size)
                }
        }
        for (index in 0 until dataList.size - 1) {
            // 연속 2일 근무는 할수 없다
            if (dataList[index].name == dataList[index + 1].name) {
                val temp = dataList[index].name
                dataList[index].name = dataList[index + 1].name
                dataList[index + 1].name = temp
            }
        }
        dataList.forEach { (month, day, dow, name) ->
            print("${month}월 ${day}일 ${dow} ${name}\n")
        }
    }
}