import os

import requests
from requests.auth import HTTPDigestAuth
from selenium import webdriver

driver = webdriver.Chrome()
driver.get('URL')
anchors = driver.find_elements_by_tag_name('a')

hrefs = [it for it in [anc.get_attribute('href') for anc in anchors] if it and it != '']
driver.close()

searchTerms = ['filterTerms']

downloadFiles = {term: [link for link in hrefs if term in link] for term in searchTerms}

print(downloadFiles)
for term, links in downloadFiles.items():
    os.makedirs(term, exist_ok=True)
    for link in links:
        # wget.download(link, term + '/' + os.path.basename(link))
        r = requests.get(link, auth=HTTPDigestAuth('Username', 'Password'))

        with open(term + '/' + os.path.basename(link), 'wb') as f:
            f.write(r.content)
