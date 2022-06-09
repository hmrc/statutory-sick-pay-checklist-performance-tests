/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov

import uk.gov.hmrc.Page.{ContentPage, FormPage}
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import java.time.LocalDate

package object hmrc extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("statutory-sick-pay-checklist-frontend")
  val route: String = "/fill-online/statutory-sick-pay-employer-notification"

  private val dob = LocalDate.of(2000, 2, 1)
  private val sicknessStartDate = LocalDate.now.minusDays(10)
  private val sicknessEndDate = sicknessStartDate.plusDays(1)
  private val lastWorkedDate = sicknessStartDate.minusDays(1)

  val journey: List[Page] = List(
    ContentPage("Navigate To Start Page", ""),
    FormPage("What Is Your Name Page", "what-is-your-name", "firstname" -> "foo", "surname" -> "bar"),
    FormPage("Do You Know Your Nino Page", "do-you-know-your-national-insurance-number", "value" -> "true"),
    FormPage("What Is Your Nino Page", "what-is-your-national-insurance-number", "value" -> "AA123456A"),
    FormPage("What Is Your Date Of Birth Page", "what-is-your-date-of-birth", fieldsForDate(dob): _*),
    FormPage("What Is Your Phone Number Page", "what-is-your-phone-number", "value" -> "07777777777"),
    FormPage("Brief Details of Your Sickness Page", "details-of-sickness", "value" -> "details"),
    FormPage("Sickness Start Date Page", "date-sickness-began", fieldsForDate(sicknessStartDate): _*),
    FormPage("Has Sickness Ended Page", "has-sickness-ended", "value" -> "true"),
    FormPage("Sickness End Date Page", "date-sickness-ended", fieldsForDate(sicknessEndDate): _*),
    FormPage("Sickness Caused By Accident Or Disease", "caused-by-accident-or-industrial-disease", "value" -> "true"),
    FormPage("Last Day Worked Before Sickness Page", "when-did-you-last-work", fieldsForDate(lastWorkedDate): _*),
    FormPage("What Time Did You Finish Page", "what-time-did-you-finish", "value" -> "9am"),
    FormPage("Do You Know Your Clock Or Payroll Number Page", "do-you-know-your-clock-or-payroll-number", "value" -> "true"),
    FormPage("What Is Your Clock Or Payroll Number Page", "what-is-your-clock-or-payroll-number", "value" -> "cprn"),
    ContentPage("Check Your Answers Page", "check-your-answers"),
    ContentPage("Next Steps Page", "next-steps"),
    ContentPage("Print Form Page", "print-form")
  )

  private def fieldsForDate(date: LocalDate): List[(String, String)] = List(
    "value.day"   -> date.getDayOfMonth.toString,
    "value.month" -> date.getMonthValue.toString,
    "value.year"  -> date.getYear.toString
  )
}
