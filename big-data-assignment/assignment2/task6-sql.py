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

    pid = parkId('plate_id')
    rid = parkId('registration_state')

    result = spark.sql(" \
        SELECT P.plate_id, P.registration_state, COUNT(*) AS num FROM pv P \
        GROUP BY P.plate_id, P.registration_state \
        ORDER BY COUNT(*) desc, P.plate_id asc \
        LIMIT 20")
    
    result.select(format_string('%s,%s\t%d', \
        result.plate_id,
        result.registration_state,
        result.num
    )).write.save("task6-sql.out",format="text")

if __name__ == "__main__":
	sc = SparkContext()
	pv=sys.argv[1]
	# ov=sys.argv[2]
	run(sc, pv)
	sc.stop()
