(ns cards.views
  (:require [re-frame.core :as re-frame]
            [cards.components.card :refer [card]]
            [cards.components.new-card-form :refer [new-card-form]]))

(defn- all-cards [cards]
  [:div.card-columns
   (for [c cards] ^{:key (str (:type c) (:value c))}
     [card c])])

(defn main []
  (let [cards (re-frame/subscribe [:cards])]
    (fn []
      [:div.container
       (all-cards @cards)
       [:div.row.mt-5
         [:div.col-sm-4.offset-sm-4.text-center
          [:h2.text-dark "New card"]
          [new-card-form #(re-frame/dispatch [:new-card %])]]]])))
