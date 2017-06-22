(ns cards.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cards.events]
            [cards.subs]
            [cards.views :as views]
            [cards.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
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
