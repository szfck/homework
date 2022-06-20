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
preCnt = 0
maxKey = None
maxCnt = 0
for line in sys.stdin:
	key, value = line.split('\t', 1)
	value = value.strip()

	if key == preKey:
		preCnt += 1
	else:
		if preKey:
			if preCnt > maxCnt:
				maxCnt = preCnt
				maxKey = preKey
		preKey = key
		preCnt = 1

if preKey:
	if preCnt > maxCnt:
		maxCnt = preCnt
		maxKey = preKey

print('{}\t{}'.format(maxKey, maxCnt))

		




