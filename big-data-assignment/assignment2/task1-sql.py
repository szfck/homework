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

def format(row):
    return '{}\t{}, {}, {}, {}'.format(
        row[0],
        row[parkId('plate_id')],
        row[parkId('violation_precinct')],
        row[parkId('violation_code')],
        row[parkId('issue_date')]
    )

def run(sc, pv, ov):
    spark = SparkSession.builder.appName("Python Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()
    pv = spark.read.format('csv').options(header='true',inferschema='true').load(pv)
    pv.createOrReplaceTempView("pv")

    ov = spark.read.format('csv').options(header='true',inferschema='true').load(ov)
    ov.createOrReplaceTempView("ov")

    # result = spark.sql("SELECT * FROM pv P where P.summons_number NOT IN (SELECT O.summons_number FROM ov O)")
    result = spark.sql("SELECT P.summons_number, P.plate_id, P.violation_precinct, P.violation_code, P.issue_date \
      FROM pv P LEFT OUTER JOIN ov O ON P.summons_number = O.summons_number WHERE O.summons_number IS NULL")

    result.select(format_string('%d\t%s, %d, %d, %s', \
        result.summons_number,
        result.plate_id,
        result.violation_precinct,
        result.violation_code,
        date_format(result.issue_date,'yyyy-MM-dd')
    )).write.save("task1-sql.out",format="text")

if __name__ == "__main__":
	sc = SparkContext()
	pv=sys.argv[1]
	ov=sys.argv[2]
	run(sc, pv, ov)
	sc.stop()
