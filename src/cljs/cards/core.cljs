(ns cards.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cards.events]
            [cards.subs]
            [cards.views :as views]
            [cards.config :as config]
            twitter-fetcher))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (let [conf (clj->js {:profile {:screenName "alepeino"}
                         :domId ""
                         :maxTweets 1
                         :enableLinks true
                         :showUser true
                         :showTime true
                         :customCallback prn
                         :showImages true})]
      (twitter-fetcher/fetch conf))
    ;(js->clj)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

(init)
