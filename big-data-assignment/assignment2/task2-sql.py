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

def run(sc, pv):
    spark = SparkSession.builder.appName("Python Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()
    pv = spark.read.format('csv').options(header='true',inferschema='true').load(pv)
    pv.createOrReplaceTempView("pv")

    result = spark.sql("SELECT P.violation_code, COUNT(*) AS num FROM pv P GROUP BY P.violation_code")

    result.select(format_string('%d\t%d', \
        result.violation_code,
        result.num
    )).write.save("task2-sql.out",format="text")

if __name__ == "__main__":
	sc = SparkContext()
	pv=sys.argv[1]
	# ov=sys.argv[2]
	run(sc, pv)
	sc.stop()
