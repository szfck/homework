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

import heapq

minHeap = []
# maxHeap = []

total = 0

def add(key, value):
	global total, minHeap #, maxHeap
	total += 1
	heapq.heappush(minHeap, (int(value), key))
	# heapq.heappush(maxHeap, (-int(value), key))

	if total > 20:
		heapq.heappop(minHeap)
		# heapq.heappop(maxHeap)

def out():
	result = []
	for i in range(20):
		value, key = heapq.heappop(minHeap)
		value = -int(value)
		result.append((value, key))
		# value, key = heapq.heappop(maxHeap)
		# value = int(value)
		# result.append((value, key))
	result.sort()
	for value, key in result:
		print('{}\t{}'.format(key, -int(value)))
		
preKey = None
preCnt = 0

for line in sys.stdin:
	key, value = line.split('\t', 1)
	value = value.strip()

	if key == preKey:
		preCnt += 1
	else:
		if preKey:
			add(preKey, preCnt)
		preKey = key
		preCnt = 1

if preKey:
	add(preKey, preCnt)
	#print(formatOut(preKey, preCnt))

out()
#print(formatOut(maxKey, maxCnt))

		




