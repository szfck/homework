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

def format(cid, weekDay, weekendDay):
    return '{}\t{}, {}'.format(
        cid, 
        '%.2f' % (weekendDay / 8.0),
        '%.2f' % (weekDay / 23.0)
    )

weekendDays = [5, 6, 12, 13, 19, 20, 26, 27]
def isWeekDay(time):
	day = int(time.split('-', 2)[2])
	if day in weekendDays:
		return False
	else:
		return True

def run(sc, pv):
	pv = sc.textFile(pv, 1)
	pv = pv.mapPartitions(lambda x: reader(x))
    
	cid = parkId('violation_code')
	tid = parkId('issue_date')
	result = pv.map(lambda x : (x[cid], (1, 0) if isWeekDay(x[tid]) else (0, 1))) \
		.reduceByKey(lambda x, y: (x[0] + y[0], x[1] + y[1])) \
		.map(lambda x : format(x[0], x[1][0], x[1][1]))

	result.saveAsTextFile("task7.out")

if __name__ == "__main__":
	sc = SparkContext()
	pv=sys.argv[1]
	# ov=sys.argv[2]
	run(sc, pv)
	sc.stop()





