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

def format(key, tot, cnt):
    avg = float(tot * 1.0 / cnt)
    return '{}\t{:.2f}, {:.2f}'.format(key, tot, avg)

def run(sc, ov):
	ov = sc.textFile(ov, 1)
	ov = ov.mapPartitions(lambda x: reader(x))

	result = ov.map(lambda x : (x[openId('license_type')], float(x[openId('amount_due')]))) \
            .combineByKey( 
                lambda x : (x, 1), # create a combiner 
                lambda x, v: (x[0] + v, x[1] + 1), # merge value
                lambda x, y : (x[0] + y[0], x[1] + y[1]) # merge combiners
            ).map(lambda x: format(x[0], x[1][0], x[1][1]))
	# result.map(lambda x: 1)

	result.saveAsTextFile("task3.out")


if __name__ == "__main__":
	sc = SparkContext()
	# pv=sys.argv[1]
	ov=sys.argv[1]
	run(sc, ov)
	sc.stop()





