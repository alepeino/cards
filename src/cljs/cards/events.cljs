(ns cards.events
  (:require [re-frame.core :as re-frame]
            [cards.db :as db]))

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
  :new-card
  (fn [db [_ card]]
    (update-in db [:cards] conj card)))
