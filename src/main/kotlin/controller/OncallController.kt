package controller

import view.InputView
import view.OutputView

class OncallController {
    fun start() {
        // 월과 시작 요일 입력 받기
        val monthAndDayOfTheWeek = InputView.readMonthAndDayOfTheWeek()
        val month = monthAndDayOfTheWeek.first
        val startDayOfTheWeek = monthAndDayOfTheWeek.second
        var weekDayStaffList = mutableListOf<String>()
        var weekEndStaffList = mutableListOf<String>()

        while(true) {
            try {
                // 평일 사원 입력 받기
                weekDayStaffList = InputView.readWeekDayStaff()
                // 휴일(토,일,법정공휴일) 사원
                weekEndStaffList = InputView.readWeekEndStaff()

            } catch (e: IllegalArgumentException) {
                println(e.message ?: "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
            }
            println()
            OutputView.printEmergenyTimeTable(month,startDayOfTheWeek,weekDayStaffList,weekEndStaffList)
            break
        }
    }
}