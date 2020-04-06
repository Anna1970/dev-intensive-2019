package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion():String = when(question){
       Question.NAME -> Question.NAME.question
       Question.PROFESSION -> Question.PROFESSION.question
       Question.MATERIAL -> Question.MATERIAL.question
       Question.BDAY -> Question.BDAY.question
       Question.SERIAL -> Question.SERIAL.question
       Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String, Triple< Int, Int, Int>>{
        return (if (question.answers.contains(answer)){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else{
            if (status.equals(Status.CRITICAL)){
                status = status.nextStatus()
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        })
    }

    enum class Status (val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus() : Status {
            return if (this.ordinal < values().lastIndex){
                values()[this.ordinal +1]
            }
            else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME(question = "Как меня зовут?", answers = listOf("бендер","bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION(question = "Назови мою профессию?", answers = listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL(question = "Из чего я сделан?",
                    answers = listOf("металл","дерево","metal","iron","wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY(question = "Когда меня создали?", answers = listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL(question = "Мой серийный номер?", answers = listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE(question = "На этом все, вопросов больше нет", answers = listOf()){
            override fun nextQuestion(): Question = NAME
        };

        abstract fun  nextQuestion() : Question
    }
}