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

weekendDays = [5, 6, 12, 13, 19, 20, 26, 27]

for line in sys.stdin:
		row = csv.reader([line], delimiter = ',')
		row = list(row)[0]
		key = row[parkId('violation_code')]
		time = row[parkId('issue_date')]
		
		day = int(time.split('-', 2)[2])
		
		weekDay = 0
		weekendDay = 0

		if day in weekendDays:
			weekendDay += 1
		else:
			weekDay += 1

		print('{}\t{}, {}'.format(key, weekDay, weekendDay))


