
# coding: utf-8

# In[1]:

import sys
import psycopg2


# In[2]:


con = psycopg2.connect(host='localhost', database='one',user='postgres', password='106868')


# In[3]:


cur = con.cursor()
query = ('select DISTINCT game_id, name from game join game_user using(game_id) where active')
outputquery = "COPY ({0}) TO STDOUT WITH delimiter ';' CSV HEADER".format(query)
with open('game_title.data', 'w+') as f:
    cur.copy_expert(outputquery, f)


# In[4]:


c = con.cursor()
query = ('select user_system_id, game_id, review from game_user where active')
outputquery = "COPY ({0}) TO STDOUT WITH delimiter ';' CSV HEADER".format(query)

with open('game.data', 'w+') as f:
    c.copy_expert(outputquery, f)


# In[5]:


cur.close()
c.close()


# In[6]:


import numpy as np
import pandas as pd


# In[7]:


df = pd.read_csv('game.data', sep=';')


# In[8]:




# In[9]:


game_titles = pd.read_csv('game_title.data', sep=';')


# In[10]:




# In[11]:


df = pd.merge(df, game_titles, on='game_id')


# In[12]:


# In[13]:


import matplotlib.pyplot as plt
import seaborn as sns

# In[14]:


sns.set_style('white')


# In[15]:


df.groupby('game_id')['review'].mean().sort_values(ascending=False).head(10)


# In[16]:


df.groupby('game_id')['review'].count().sort_values(ascending=False).head(10)


# In[17]:


ratings =pd.DataFrame(df.groupby('game_id')['review'].mean())


# In[18]:




# In[19]:


ratings['rating_numbers'] = pd.DataFrame(df.groupby('game_id')['review'].count())


# In[20]:




# In[21]:


ratings['rating_numbers'].hist(bins=70)


# In[22]:


ratings['review'].hist(bins=70)


# In[23]:


sns.jointplot(x='review', y='rating_numbers', data=ratings, alpha=0.5)


# ## Sistema de Recomendação

# In[24]:


gamemat = df.pivot_table(index='user_system_id', columns=['game_id'], values='review')


# ##### Jogos mais avaliados

# In[25]:


ratings.sort_values('rating_numbers', ascending=False).head(10)


# In[26]:

game_user_ratings = gamemat[int(sys.argv[1])]


# In[27]:


# In[28]:


similar_game_user_ratings = gamemat.corrwith(game_user_ratings)


# In[29]:


corr_game_user_ratings = pd.DataFrame(similar_game_user_ratings, columns=['Correlation'])


# In[30]:


corr_game_user_ratings.dropna(inplace=True)


# In[31]:


corr_game_user_ratings = corr_game_user_ratings.join(ratings['rating_numbers'], how='left')


# In[32]:


result = corr_game_user_ratings[corr_game_user_ratings['rating_numbers']>2].sort_values('Correlation', ascending=False).head()
result.to_csv('result_file', sep=';', encoding='utf-8')
