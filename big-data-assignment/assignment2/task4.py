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

from csv import reader
import sys
from pyspark import SparkContext


# PV = '/user/ecc290/HW1data/parking-violations.csv'
# OV = '/user/ecc290/HW1data/open-violations.csv'

def format(row):
    return '{}\t{}'.format(
        row[0],
        row[1],
    )

def run(sc, pv):
	pv = sc.textFile(pv, 1)
	pv = pv.mapPartitions(lambda x: reader(x))

	pid = parkId('registration_state')
	result = pv.map(lambda x : (x[pid] if x[pid]=='NY' else 'Other', 1))  \
            .reduceByKey(lambda x, y: x + y) \
            .map(lambda x : format(x))

	result.saveAsTextFile("task4.out")

if __name__ == "__main__":
	sc = SparkContext()
	pv=sys.argv[1]
	# ov=sys.argv[2]
	run(sc, pv)
	sc.stop()





