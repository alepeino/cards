(ns cards.core
  (:require
    [cards.config :as config]
    [cards.events]
    [cards.subs]
    [cards.views :as views]
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
