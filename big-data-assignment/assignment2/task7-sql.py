parkArray = [
    'summons_number',
    'issue_date',
    'violation_code',
    'violation_county',
    'violation_description',
    'violation_location',
    'violation_precinct',
    'violation_time',
    'time_first_observed',
    'meter_number',
    'issuer_code',
    'issuer_command',
    'issuer_precinct',
    'issuing_agency',
    'plate_id',
    'plate_type',
    'registration_state',
    'street_name',
    'vehicle_body_type',
    'vehicle_color',
    'vehicle_make',
    'vehicle_year'
]

openArray = [
    'summons_number',
    'plate',
    'license_type',
    'county',
    'state',
    'precinct',
    'issuing_agency',
    'violation',
    'violation_status',
    'issue_date',
    'violation_time',
    'judgment_entry_date',
    'amount_due',
    'payment_amount',
    'penalty_amount',
    'fine_amount',
    'interest_amount',
    'reduction_amount'
]

def parkId(value):
    return parkArray.index(value)

def openId(value):
    return openArray.index(value)

import sys
from pyspark import SparkContext
from pyspark.sql import SparkSession
from pyspark.sql.functions import *
# saveas = "task1.out"

# PV = '/user/ecc290/HW1data/parking-violations.csv'
# OV = '/user/ecc290/HW1data/open-violations.csv'

weekendDays = [5, 6, 12, 13, 19, 20, 26, 27]
def isWeekDay(time):
	day = int(time.split('-', 2)[2])
	if day in weekendDays:
		return False
	else:
		return True

def run(sc, pv):
    spark = SparkSession.builder.appName("Python Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()
    pv = spark.read.format('csv').options(header='true',inferschema='true').load(pv)
    pv.createOrReplaceTempView("pv")
# IF(A.violation_code = NONE B.violation_code, A.violation_code)
        # SELECT A.violation_code AS vio, IsNull(weekend, 0) AS we, IsNull(weekday, 0) AS wd FROM \

    spark.sql(" \
        SELECT A.violation_code, COUNT(*) / 8.0 AS weekend FROM pv A \
        WHERE \
        A.issue_date LIKE '%03-05%' OR \
        A.issue_date LIKE '%03-06%' OR \
        A.issue_date LIKE '%03-12%' OR \
        A.issue_date LIKE '%03-13%' OR \
        A.issue_date LIKE '%03-19%' OR \
        A.issue_date LIKE '%03-20%' OR \
        A.issue_date LIKE '%03-26%' OR \
        A.issue_date LIKE '%03-27%' \
        GROUP BY A.violation_code \
    ").createOrReplaceTempView("weekendTable")


    spark.sql(" \
        SELECT B.violation_code, COUNT(*) / 23.0 AS weekday FROM pv B \
        WHERE \
        B.issue_date LIKE '%03-01%' OR \
        B.issue_date LIKE '%03-02%' OR \
        B.issue_date LIKE '%03-03%' OR \
        B.issue_date LIKE '%03-04%' OR \
        B.issue_date LIKE '%03-07%' OR \
        B.issue_date LIKE '%03-08%' OR \
        B.issue_date LIKE '%03-09%' OR \
        B.issue_date LIKE '%03-10%' OR \
        B.issue_date LIKE '%03-11%' OR \
        B.issue_date LIKE '%03-14%' OR \
        B.issue_date LIKE '%03-15%' OR \
        B.issue_date LIKE '%03-16%' OR \
        B.issue_date LIKE '%03-17%' OR \
        B.issue_date LIKE '%03-18%' OR \
        B.issue_date LIKE '%03-21%' OR \
        B.issue_date LIKE '%03-22%' OR \
        B.issue_date LIKE '%03-23%' OR \
        B.issue_date LIKE '%03-24%' OR \
        B.issue_date LIKE '%03-25%' OR \
        B.issue_date LIKE '%03-28%' OR \
        B.issue_date LIKE '%03-29%' OR \
        B.issue_date LIKE '%03-30%' OR \
        B.issue_date LIKE '%03-31%' \
        GROUP BY B.violation_code \
    ").createOrReplaceTempView("weekdayTable")

    result = spark.sql(" \
        (SELECT A.violation_code, A.weekend, IF(B.weekday is null, 0, B.weekday) AS weekday \
        FROM (weekendTable) A \
        LEFT JOIN (weekdayTable) B \
        ON A.violation_code=B.violation_code) \
        UNION \
        (SELECT B.violation_code, IF(A.weekend is null, 0, A.weekend) AS weekend, B.weekday \
        FROM weekendTable A \
        RIGHT OUTER JOIN weekdayTable B \
        ON A.violation_code=B.violation_code) \
    ")

    # result = spark.sql(" \
    #     SELECT IsNull(*, 0) FROM \
    #     (SELECT A.violation_code, COUNT(*) / 8.0 AS weekend FROM pv A \
    #     WHERE \
    #     A.issue_date LIKE '%03-05%' OR \
    #     A.issue_date LIKE '%03-06%' OR \
    #     A.issue_date LIKE '%03-12%' OR \
    #     A.issue_date LIKE '%03-13%' OR \
    #     A.issue_date LIKE '%03-19%' OR \
    #     A.issue_date LIKE '%03-20%' OR \
    #     A.issue_date LIKE '%03-26%' OR \
    #     A.issue_date LIKE '%03-27%' \
    #     GROUP BY A.violation_code) A \
    #     FULL OUTER JOIN \
    #     (SELECT B.violation_code, COUNT(*) / 23.0 AS weekday FROM pv B \
    #     WHERE \
    #     B.issue_date LIKE '%03-01%' OR \
    #     B.issue_date LIKE '%03-02%' OR \
    #     B.issue_date LIKE '%03-03%' OR \
    #     B.issue_date LIKE '%03-04%' OR \
    #     B.issue_date LIKE '%03-07%' OR \
    #     B.issue_date LIKE '%03-08%' OR \
    #     B.issue_date LIKE '%03-09%' OR \
    #     B.issue_date LIKE '%03-10%' OR \
    #     B.issue_date LIKE '%03-11%' OR \
    #     B.issue_date LIKE '%03-14%' OR \
    #     B.issue_date LIKE '%03-15%' OR \
    #     B.issue_date LIKE '%03-16%' OR \
    #     B.issue_date LIKE '%03-17%' OR \
    #     B.issue_date LIKE '%03-18%' OR \
    #     B.issue_date LIKE '%03-21%' OR \
    #     B.issue_date LIKE '%03-22%' OR \
    #     B.issue_date LIKE '%03-23%' OR \
    #     B.issue_date LIKE '%03-24%' OR \
    #     B.issue_date LIKE '%03-25%' OR \
    #     B.issue_date LIKE '%03-28%' OR \
    #     B.issue_date LIKE '%03-29%' OR \
    #     B.issue_date LIKE '%03-30%' OR \
    #     B.issue_date LIKE '%03-31%' \
    #     GROUP BY B.violation_code) B \
    #     ON A.violation_code=B.violation_code \
    # ").show()

    # change format from decimal to float
    result = result.select(
       result.violation_code, 
       result.weekend.cast("float"), 
       result.weekday.cast("float")
    )

    result.select(format_string('%s\t%.2f, %.2f',
        result.violation_code, 
        result.weekend,
        result.weekday
    )).write.save("task7-sql.out",format="text")

if __name__ == "__main__":
	sc = SparkContext()
	pv=sys.argv[1]
	# ov=sys.argv[2]
	run(sc, pv)
	sc.stop()
