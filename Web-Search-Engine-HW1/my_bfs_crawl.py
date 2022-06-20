from page_and_url import Page, Url
import urllib, htmllib, formatter
import numpy as np
import urlparse
from bs4 import BeautifulSoup
import datetime, time
import heapq
import relevance_strategy
import re
from mimetypes import MimeTypes
import Queue
import util
from my_crawl import My_Crawl

class My_Bfs_Crawl(My_Crawl):
    ''' bfs crawl class, use first in first out queue to store promised url '''

    def __init__(self):
        My_Crawl.__init__(self)
        self.queue = Queue.Queue() # First in First out queue to store Url class
    
    def calculate_promise(self, url, text, relevance_score, link_number):
        ''' bfs does not need promise value, set -1 as default'''
        return -1
    
    def add_to_queue(self, item):
        ''' add url with promise to fifo queue '''
        self.queue.put(item)

    def pop_from_queue(self):
        ''' pop and return the top value '''
        return self.queue.get()

    def queue_size(self):
        ''' return size of queue '''
        return self.queue.qsize()

    def need_stop(self):
        ''' stop to put link to queue early '''
        return self.queue_size() > 10000