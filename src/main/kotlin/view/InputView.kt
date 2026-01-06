package view

import camp.nextstep.edu.missionutils.Console

object InputView {
    // 비상 근무를 배정할 월과 시작 요일 입력 받기
    fun readMonthAndDayOfTheWeek(): Pair<Int, String> {
        while (true) {
            try {
                print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
                val input = Console.readLine()
                val tokens = input.split(",")
                val month = validateMonth(tokens[0])
                val day = validateDayOfTheWeek(tokens[1])
                return month to day
            } catch (e: IllegalArgumentException) {
                println(e.message ?: "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.")
            }
        }
    }

    fun validateMonth(monthString: String): Int {
        if (monthString.isBlank())
            throw IllegalArgumentException("[ERROR] 공백만 입력할 수는 없습니다. 다시 입력해 주세요.")
        if (monthString.trim() != monthString)
            throw IllegalArgumentException("[ERROR] 월 앞 뒤에 공백이 있으면 안됩니다. 다시 입력해 주세요.")
        val monthInt = monthString.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 월은 숫자 형식으로 입력해야합니다. 다시 입력해 주세요.")
        if (monthInt !in 1..12)
            throw IllegalArgumentException("[ERROR] 월은 1~12사이의 숫자를 입력해야합니다. 다시 입력해 주세요.")
        return monthInt
    }

    fun validateDayOfTheWeek(dayOfTheWeek: String): String {
        if (dayOfTheWeek.isBlank())
            throw IllegalArgumentException("[ERROR] 공백만 입력할 수는 없습니다. 다시 입력해 주세요.")
        if (dayOfTheWeek.trim() != dayOfTheWeek)
            throw IllegalArgumentException("[ERROR] 요일 앞 뒤에 공백이 있으면 안됩니다. 다시 입력해 주세요.")
        return dayOfTheWeek
    }

    fun readWeekDayStaff(): MutableList<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val input = Console.readLine()
        val weekDayStaffList = input.split(",").toMutableList()
        // 검증된 직원 리스트를 리턴할 것
        val validatedWeekDayStaffList = mutableListOf<String>()
        for (staff in weekDayStaffList) {
            val validatedWeekDayStaff = validateStaff(staff)
            validatedWeekDayStaffList.add(validatedWeekDayStaff)
        }
        // 중복 방지
        if(validatedWeekDayStaffList.toSet().size!=validatedWeekDayStaffList.size)
            throw IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        return validatedWeekDayStaffList
    }

    fun readWeekEndStaff(): MutableList<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val input = Console.readLine()
        val weekEndStaffList = input.split(",").toMutableList()
        // 검증된 직원 리스트를 리턴할 것
        val validatedWeekEndStaffList = mutableListOf<String>()
        for (staff in weekEndStaffList) {
            val validatedWeekEndStaff = validateStaff(staff)
            validatedWeekEndStaffList.add(validatedWeekEndStaff)
        }
        if(validatedWeekEndStaffList.toSet().size!=validatedWeekEndStaffList.size)
            throw IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.")
        return validatedWeekEndStaffList
    }

    // 평일과 휴일 공통으로 쓸 검증 함수
    fun validateStaff(staff: String): String {
        if (staff.isBlank())
            throw IllegalArgumentException("[ERROR] 공백만 입력할 수는 없습니다. 다시 입력해 주세요.")
        if (staff.trim() != staff)
            throw IllegalArgumentException("[ERROR] 사원 닉네임 앞 뒤에 공백이 있으면 안됩니다. 다시 입력해 주세요.")

        return staff
    }
}