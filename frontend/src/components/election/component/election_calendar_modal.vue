<template>
  <div>
    <div>
      <div class ="reward">
        <button
        id="show-btn"
        @click="rewardClick()"
        class="rtext modalButton"
      >오늘의 보상</button>
      <button
        id="show-btn"
        @click="[$bvModal.show('bv-modal-example'), modalClick()]"
        class="rtext modalButton"
      >투표 정보</button>
      </div>
      <b-modal id="bv-modal-example" hide-footer>
        <template #modal-title>
          <div class="text">
            나의 투표 정보
          </div>
        </template>
        <b-container>
          <main class="b-calendar">
            <b-row>
              <b-col md="4">
                <div class="b-calendar__information">
                  <div
                    class="today d-flex justify-content-center align-items-center"
                  >
                    <div class="weekDay">
                      {{ selectedWeekDay | capitalize }}
                    </div>
                    <div class="day">
                      {{ selectedDayAndMonth.day }}
                    </div>
                    <div class="month">
                      {{ selectedDayAndMonth.month | capitalize }}
                    </div>
                    <a
                      href="#"
                      id="goTodayLink"
                      @click="goToday"
                      v-show="
                        !todayInCurrentMonthAndYear || !todayIsEqualSelectDate
                      "
                    >
                      Today
                    </a>
                    <b-tooltip
                      target="goTodayLink"
                      v-show="
                        !todayInCurrentMonthAndYear || !todayIsEqualSelectDate
                      "
                    >
                      Back to today
                    </b-tooltip>
                  </div>
                </div>
              </b-col>
              <b-col md="8">
                <div class="b-calendar__calendar">
                  <div class="b-calendar__header">
                    <b-row>
                      <b-col class="year text-right text" align-h="end">
                        <span>{{ year }}</span>
                      </b-col>
                    </b-row>
                    <b-row align-v="center">
                      <b-col class="text-left" align-h="start">
                        <b-button
                          id="subtractMonthBtn"
                          class="arrow arrow-left"
                          variant="light"
                          @click="subtractMonth"
                        >
                          <i class="fa fa-fw fa-chevron-left"></i>
                        </b-button>
                        <b-tooltip target="subtractMonthBtn">
                          {{ previousMonthAsString | capitalize }}
                        </b-tooltip>
                      </b-col>
                      <b-col class="text-center text" align-h="center">
                        <span class="month">{{ month }}</span>
                      </b-col>
                      <b-col
                        class="text-right d-flex flex-row-reverse"
                        align-h="end"
                      >
                        <b-button
                          id="addMonthBtn"
                          class="arrow arrow-right"
                          variant="light"
                          @click="addMonth"
                        >
                          <i class="fa fa-fw fa-chevron-right"></i>
                        </b-button>
                        <b-tooltip target="addMonthBtn">
                          {{ nextMonthAsString | capitalize }}
                        </b-tooltip>
                      </b-col>
                    </b-row>
                  </div>
                  <div class="b-calendar__weekdays">
                    <div
                      class="weekday"
                      v-for="(day, index) in days"
                      :key="index"
                    >
                      <strong>{{ day }}</strong>
                    </div>
                  </div>
                  <div class="b-calendar__dates daytext">
                    <div
                      class="date text-right"
                      :class="{
                        today: date.today,
                        blank: date.blank,
                        'no-border-right': date.key % 7 === 0,
                        on: isState(date.dayNumber, date.date),
                      }"
                      v-for="date in dateList"
                      :key="date.key"
                      :data-date="date.date"
                      @click="setSelectedDate(date.moment)"
                      v-on:click="
                        [$bvModal.show('modal-content'), selecteDate()]
                      "
                    >
                      <span class="day">
                        {{ date.dayNumber }}
                      </span>
                      <span class="weekday">{{ date.weekDay }}</span>
                      <div class="additional" v-show="date.additional">
                        <span class="year" v-show="date.additional.year">{{
                          date.additional.year
                        }}</span>
                        <span class="month" v-show="date.additional.month">{{
                          date.additional.month
                        }}</span>
                      </div>
                    </div>
                  </div>
                </div>
              </b-col>
            </b-row>
          </main>
        </b-container>
        <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-example')"
          >Close</b-button
        >
      </b-modal>
      <b-modal id="modal-content" hide-footer>
        <calContent :propsDate="propsDate" />
      </b-modal>
    </div>
  </div>
</template>
<script>
import moment from 'moment';
import calContent from '@/components/election/component/election_calendar_content.vue';
import axios from 'axios';
const userId = localStorage.getItem('id');

export default {
  data() {
    return {
      today: moment(),
      dateContext: moment(),
      selectedDate: moment(),
      propsDate: '',
      days: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      checkday: [],
    };
  },
  components: {
    calContent,
  },
  computed: {
    year: function() {
      return this.dateContext.format('Y');
    },

    month: function() {
      return this.dateContext.format('MMMM');
    },

    daysInMonth: function() {
      return this.dateContext.daysInMonth();
    },

    currentDate: function() {
      return this.dateContext.get('date');
    },

    firstDayOfMonth: function() {
      let firstDay = moment(this.dateContext).subtract(
        this.currentDate,
        'days'
      );
      return firstDay.weekday();
    },

    previousMonth: function() {
      return moment(this.dateContext).subtract(1, 'month');
    },
    previousMonthAsString: function() {
      return this.previousMonth.format('MMMM');
    },
    nextMonth: function() {
      return moment(this.dateContext).add(1, 'month');
    },
    nextMonthAsString: function() {
      return this.nextMonth.format('MMMM');
    },

    daysInPreviousMonth: function() {
      return this.previousMonth.daysInMonth();
    },
    daysFromPreviousMonth: function() {
      let daysList = [];
      let count = this.daysInPreviousMonth - this.firstDayOfMonth;
      while (count < this.daysInPreviousMonth) {
        count++;
        daysList[count] = count;
      }

      return daysList.filter(function() {
        return true;
      });
    },

    dateList: function() {
      let $this = this;

      let dateList = [];

      let previousMonth = this.previousMonth;
      let nextMonth = this.nextMonth;

      //dates for display
      let formattedCurrentMonth = this.dateContext.format('MM');
      let formattedCurrentYear = this.year;
      let formattedPreviousMonth = previousMonth.format('MM');
      let formattedPreviousYear = previousMonth.format('Y');
      let formattedNextMonth = nextMonth.format('MM');
      let formattedNextYear = nextMonth.format('Y');

      //counters
      let countDayInCurrentMonth = 0;
      let countDayInPreviousMonth = 0;

      //filling in dates from the previous month
      this.daysFromPreviousMonth.forEach(function(dayFromPreviousMonth) {
        countDayInCurrentMonth++;
        countDayInPreviousMonth++;

        let formattedDay = $this.formattingDay(dayFromPreviousMonth);
        let previousMonth =
          $this.daysFromPreviousMonth.length === countDayInPreviousMonth
            ? $this.previousMonthAsString
            : false;
        let previousYear =
          formattedCurrentYear !== formattedPreviousYear &&
          $this.daysFromPreviousMonth.length === countDayInPreviousMonth
            ? formattedPreviousYear
            : false;
        let additional = {
          month: previousMonth,
          year: previousYear,
        };

        if (!previousMonth && !previousYear) {
          additional = false;
        }

        dateList[countDayInCurrentMonth] = {
          key: countDayInCurrentMonth,
          dayNumber: formattedDay,
          date:
            formattedDay +
            '.' +
            formattedPreviousMonth +
            '.' +
            formattedPreviousYear,
          blank: true,
          today: false,
          additional: additional,
          weekDay: false,
          moment: moment(
            formattedPreviousYear + formattedPreviousMonth + formattedDay
          ),
        };
      });

      //filling in dates from the current month
      while (countDayInCurrentMonth < this.firstDayOfMonth + this.daysInMonth) {
        countDayInCurrentMonth++;

        let day = countDayInCurrentMonth - countDayInPreviousMonth;
        let weekDay = this.getWeekDay(countDayInCurrentMonth);
        let formattedDay = this.formattingDay(day);

        dateList[countDayInCurrentMonth] = {
          key: countDayInCurrentMonth,
          dayNumber: formattedDay,
          date:
            formattedDay +
            '.' +
            formattedCurrentMonth +
            '.' +
            formattedCurrentYear,
          blank: false,
          today:
            formattedDay === this.initialDate &&
            this.todayInCurrentMonthAndYear,
          additional: false,
          weekDay: weekDay,
          moment: moment(
            formattedCurrentYear + formattedCurrentMonth + formattedDay
          ),
        };
      }

      let daysInNextMonth = 7 - (countDayInCurrentMonth % 7);
      let countDayInCurrentMonthSaved = countDayInCurrentMonth;
      let day = 0;

      //filling in dates from the next month
      if (daysInNextMonth < 7) {
        while (
          countDayInCurrentMonth <
          countDayInCurrentMonthSaved + daysInNextMonth
        ) {
          countDayInCurrentMonth++;
          day++;

          let formattedDay = this.formattingDay(day);
          let nextMonth = day === 1 ? this.nextMonthAsString : false;
          let nextYear =
            formattedCurrentYear !== formattedNextYear && day === 1
              ? formattedNextYear
              : false;
          let additional = {
            month: nextMonth,
            year: nextYear,
          };

          if (!nextMonth && !nextYear) {
            additional = false;
          }

          dateList[countDayInCurrentMonth] = {
            key: countDayInCurrentMonth,
            dayNumber: formattedDay,
            date:
              formattedDay + '.' + formattedNextMonth + '.' + formattedNextYear,
            blank: true,
            today: false,
            additional: additional,
            weekDay: false,
            moment: moment(
              formattedNextYear + formattedNextMonth + formattedDay
            ),
          };
        }
      }

      return dateList.filter(function() {
        return true;
      });
    },

    initialDate: function() {
      return this.formattingDay(this.today.get('date'));
    },
    initialMonth: function() {
      return this.today.format('MMMM');
    },
    initialYear: function() {
      return this.today.format('Y');
    },
    todayInCurrentMonthAndYear: function() {
      return this.month === this.initialMonth && this.year === this.initialYear;
    },
    selectedDayAndMonth: function() {
      let dayAndMonth = this.selectedDate.format('D MMMM');
      dayAndMonth = dayAndMonth.split(' ');
      dayAndMonth = {
        day: dayAndMonth[0],
        month: dayAndMonth[1],
      };

      return dayAndMonth;
    },
    selectedWeekDay: function() {
      return this.selectedDate.format('dddd');
    },
    todayIsEqualSelectDate: function() {
      return (
        this.selectedDate.format('YYYYMMDD') === this.today.format('YYYYMMDD')
      );
    },
  },
  methods: {
    addMonth: function() {
      this.dateContext = this.nextMonth;
    },
    subtractMonth: function() {
      this.dateContext = this.previousMonth;
    },
    setSelectedDate: function(moment) {
      this.selectedDate = moment;
    },
    goToday: function() {
      this.selectedDate = this.today;
      this.dateContext = this.today;
    },
    formattingDay(day) {
      return ('0' + day).slice(-2);
    },
    getWeekDay(day) {
      let index = day;
      if (index > 7) {
        index %= 7;
      }
      index = index === 0 ? 6 : index - 1;
      return this.days[index];
    },
    selecteDate: function() {
      this.propsDate = this.selectedDate.format('YYYY-MM-DD');
      console.log(this.propsDate);
      console.log(this.dateList.dayNumber);
    },
    modalClick: function() {
      this.propsDate = this.selectedDate.format('YYYY-MM');
      var year = this.selectedDate.format('YYYY');
      var month = this.selectedDate.format('MM');
      axios
        .get(
          'http://j4b107.p.ssafy.io/api/elections/content/' +
            userId +
            '/' +
            year +
            '/' +
            month
        )
        .then((response) => {
          this.checkday = response.data.localDates;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    isState: function(day, month) {
      var monthTemp = month.split('.');
      for (var d in this.checkday) {
        var temp = this.checkday[d].split('-');
        if (day === temp[2] && temp[1] === monthTemp[1]) {
          return true;
        } else false;
      }
    },
    rewardClick : function () {
      var rewardResponse;
       axios
        .get(
          'http://j4b107.p.ssafy.io/api/election/isrewarded/' +
            userId +
            '/REWARD'
        )
        .then((response) => {
          rewardResponse = response.data;
          if(rewardResponse==true){
          alert("이미 오늘의 보상을 받으셨습니다.");
        }else{
          alert("보상이 지급될 때 까지 잠시만 기다려주세요");
        }
          })
        .catch((error) => {
          console.log(error);
        });
        
    }
  },
  filters: {
    capitalize: function(value) {
      if (!value) return '';
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    },
  },
};
</script>
<style scoped>
@font-face {
  font-family: 'account_font';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
.text {
  font-family: 'account_font';
  font-size: x-large;
}
.rtext{
  font-family: 'account_font';
  font-size: large;
  border: solid 2px #0c3267;
  border-radius: 20px;
      margin-left: 10px;

}
.daytext {
  font-family: 'account_font';
  font-size: large;
}
.icon {
  display: inline-block;
  width: 1em;
  height: 1em;
  stroke-width: 0;
  stroke: currentColor;
  fill: currentColor;
}
.mt-100 {
  margin-top: 100px;
}
.modalButton {
  width: 10%;
  height: 10%;
  background-color: white;
}
.reward{
  display: flex;
      place-content: center;

}
.modal-button {
  background-color: rgb(29, 226, 226);
  border-color: rgb(29, 226, 226);
  border-radius: 6px;
  color: white;
  font-size: 17px;
  padding-right: 76px;
  padding-left: 76px;
}

.card {
  border-radius: 3vh;
  margin: auto;
  max-width: 380px;
  padding: 7vh 6vh;
  align-items: center;
  box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

@media (max-width: 767px) {
  .card {
    width: 90vw;
  }
}

.card-img {
  padding: 20px 0;
  width: 40%;
}

.card-img img {
  opacity: 0.7;
}

.card-title {
  margin-bottom: unset;
}

.card-title p {
  color: rgb(29, 226, 226);
  font-weight: 900;
  font-size: 30px;
  margin-bottom: unset;
}

.card-text p {
  color: grey;
  font-size: 25px;
  text-align: center;
  padding: 3vh 0;
  font-weight: lighter;
}

.btn {
  width: 100%;
  background-color: white;
  border-color: white;
  border-radius: 25px;
  color: #0c3267;
  font-size: 20px;
}

.btn:focus {
  box-shadow: none;
  outline: none;
  box-shadow: none;
  color: white;
  -webkit-box-shadow: none;
  -webkit-user-select: none;
  transition: none;
}

.btn:hover {
  color: white;
}
.b-calendar {
  display: flex;
  align-items: center;
  margin: 2.5em 0;
}
.b-calendar__information {
  background-color: #0c3267;
  border-radius: 1.2rem 0 0 1.2rem;
  height: 100%;
}
.b-calendar__information .today {
  flex-direction: column;
  padding-top: 3em;
}
.b-calendar__information .today .weekDay {
  font-size: 1.2em;
  font-weight: 100;
  padding-bottom: 0.5em;
}
.b-calendar__information .today .day {
  font-size: 5.5em;
  font-weight: 600;
  line-height: 1;
}
.b-calendar__information .today .month {
  font-size: 2em;
  font-weight: 200;
  line-height: 1;
}
.b-calendar__calendar {
  min-height: 40rem;
}
.b-calendar__header {
  margin-bottom: 2rem;
}
.b-calendar__header .month {
  font-size: 1.25em;
  font-weight: 200;
  text-transform: capitalize;
}
.b-calendar__header .year {
  font-size: 1.5em;
  font-weight: 600;
  margin-bottom: 1rem;
}
.b-calendar__header .arrow {
  background: transparent;
  border: 2px solid #0c3267;
  border-radius: 50%;
  color: #0c3267;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 2.5rem;
  width: 2.5rem;
}
.b-calendar__header .arrow:hover {
  cursor: pointer;
}
.b-calendar__header .arrow-left i {
  width: 0;
  height: 0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-right: 10px solid #0c3267;
}
.b-calendar__header .arrow-right i {
  width: 0;
  height: 0;
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-left: 10px solid #0c3267;
}
.b-calendar__weekdays {
  display: flex;
  margin-bottom: 1.25rem;
}
.b-calendar__weekdays .weekday {
  width: calc(100% / 7);
  padding: 0.25rem 0.5rem;
}
.b-calendar__dates {
  display: flex;
  flex-wrap: wrap;
  position: relative;
}
.b-calendar__dates:after {
  content: '';
  position: absolute;
  bottom: 0;
  background-color: #fff;
  height: 1px;
  width: 100%;
  z-index: 1;
}
.b-calendar__dates .date {
  border-right: 1px solid rgba(0, 0, 0, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.05);
  font-weight: 200;
  min-height: 4.5rem;
  padding: 0.25rem 0.5rem;
  position: relative;
  width: calc(100% / 7);
}
.b-calendar__dates .date.blank {
  background-color: rgba(0, 0, 0, 0.02);
  color: rgba(0, 0, 0, 0.2);
}
.b-calendar__dates .date.no-border-right {
  border-right: none;
}
.b-calendar__dates .date.today {
  background-color: rgba(0, 0, 0, 0.2);
}
.b-calendar__dates .on {
  background-color: grey;
}
.b-calendar__dates .date .weekday {
  display: none;
}
.b-calendar__dates .date .additional {
  font-size: 0.75em;
  position: absolute;
  left: 0.5rem;
}
.b-calendar__dates .date .additional .year {
  padding-right: 0.25rem;
  font-size: 0.75em;
}
.today {
  font-family: 'account_font';
  font-size: large;
  color: white;
}
@media (max-width: 768px) {
  .b-calendar__information {
    min-height: auto;
    padding-top: 2rem;
    padding-bottom: 2rem;
    border-radius: 2.5rem 2.5rem 0 0;
  }
  .b-calendar__information .today {
    padding-top: 0;
  }
}
@media (max-width: 480px) {
  .b-calendar__weekdays {
    display: none;
  }
  .b-calendar__dates .date {
    width: 100%;
    text-align: left !important;
    border: none;
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    display: flex;
  }
  .b-calendar__dates .date.blank {
    display: none;
  }
  .b-calendar__dates .date .weekday {
    display: block;
    margin-left: 0.25rem;
  }
}
</style>
