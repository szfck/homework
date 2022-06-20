#!/usr/bin/env python
import sys
import csv
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

preKey = None
preWeekCnt = 0
preWeekendCnt = 0
for line in sys.stdin:
	key, value = line.split('\t', 1)
	value = value.strip().split(',')
	weekDay = int(value[0])
	weekendDay = int(value[1])

	if key == preKey:
		preWeekCnt += weekDay
		preWeekendCnt += weekendDay
	else:
		if preKey:
			print('{}\t{}, {}'.format(preKey, '% .2f' % (preWeekendCnt / 8.0), '%.2f' % (preWeekCnt / 23.0)))
		preKey = key
		preWeekCnt = weekDay
		preWeekendCnt = weekendDay

if preKey:
	print('{}\t{}, {}'.format(preKey, '% .2f' % (preWeekendCnt / 8.0), '%.2f' % (preWeekCnt / 23.0)))

		




