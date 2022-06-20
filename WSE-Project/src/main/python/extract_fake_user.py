#!/usr/bin/env python
# coding=utf-8
import json
import time
from datetime import datetime

start = time.time()

users = {}
bs = {}
reviews = {}

with open('./yelp_academic_dataset_review.json') as f:
    for line in f:
        r = json.loads(line)
        if r['user_id'] not in users:
            users[r['user_id']] = {
                'date_reviews': {},
                'five_stars': 0,
                'one_stars': 0,
                'reviews': 0,
                'first_review_date': datetime.strptime(r['date'], "%Y-%m-%d"),
                'last_review_date': datetime.strptime(r['date'], "%Y-%m-%d")
            }
        u = users[r['user_id']]
        date_reviews = u['date_reviews']
        if r['date'] not in date_reviews:
            date_reviews[r['date']] = 1
        else:
            date_reviews[r['date']] += 1
        u['reviews'] += 1
        if r['stars'] == 5:
            u['five_stars'] += 1
        elif r['stars'] == 1:
            u['one_stars'] += 1
        d = datetime.strptime(r['date'], "%Y-%m-%d")
        if d < u['first_review_date']:
            u['first_review_date'] = d
        elif d > u['last_review_date']:
            u['last_review_date'] = d
        if r['business_id'] not in bs:
            bs[r['business_id']] = {
                'date_reviews': {},
                'date_review_users': {}
            }
        b = bs[r['business_id']]
        date_reviews = b['date_reviews']
        date_review_users = b['date_review_users']
        if r['date'] not in date_reviews:
            date_reviews[r['date']] = 1
        else:
            date_reviews[r['date']] += 1
        if r['date'] not in date_review_users:
            date_review_users[r['date']] = set()
        date_review_users[r['date']].add(r['user_id'])
        if len(r['text']) >= 20:
            if r['text'] not in reviews:
                reviews[r['text']] = []
            reviews[r['text']].append(r)

fake_users = set()

for user_id, u in users.items():
    if u['reviews'] >= 10 and (u['five_stars'] * 100 // u['reviews'] >= 90 or u['one_stars'] * 100 // u['reviews'] >= 90):
        if (u['last_review_date'] - u['first_review_date']).total_seconds() < 2592000000:
            fake_users.add(user_id)
    for date, date_review in u['date_reviews'].items():
        over_reviews = 0
        if date_review >= 3:
            over_reviews += 1
    if len(u['date_reviews']) >= 5 and over_reviews * 100 // len(u['date_reviews']) >= 80:
         fake_users.add(user_id)                

for review_text, review_orgi in reviews.items():
    if len(review_orgi) > 1:
        for review in review_orgi:
            fake_users.add(review['user_id'])

print('fake_users:' + str(len(fake_users)) + ' total_users:' + str(len(users)) + ' ' + str(len(fake_users) * 10000 // len(users)) + '%%')

total = 0
fake = 0

with open('./yelp_academic_dataset_review.json') as f:
    for line in f:
        r = json.loads(line)
        total += 1
        if r['user_id'] in fake_users:
            fake += 1

print('fake_reviews:' + str(fake) + ' total_reviews:' + str(total) + ' ' + str(fake * 10000 // total) + '%%')

print('finish in ' + str(time.time() - start) + 's')
