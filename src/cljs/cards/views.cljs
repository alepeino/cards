(ns cards.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r]
            [reagent-forms.core :refer [bind-fields]]
            [cards.components.card :refer [card]]))

(defn- all-cards [cards]
  [:div.card-columns
     (map-indexed card (vec cards))])

(defn row [label input]
  [:div.row
   [:div.col-md-2 [:label label]]
   [:div.col-md-5 input]])

(def form-template
  [:div
   (row "first name"
        [:input.form-control {:field :text :id :first-name}])
   (row "last name"
        [:input.form-control {:field :text :id :last-name}])
   (row "age"
        [:input.form-control {:field :numeric :id :age}])
   (row "email"
        [:input.form-control {:field :email :id :email}])
   (row "comments"
        [:textarea.form-control {:field :textarea :id :comments}])])

(defn form []
  (let [data (r/atom {:first-name "John" :last-name "Doe" :age 35})]
    (fn []
      [:form.form-horizontal.text-dark
       [:div.page-header [:span.h2 "New"]]
       [bind-fields form-template data]
       [:label (str @data)]])))

(defn main []
  (let [cards (re-frame/subscribe [:cards])]
    (fn []
      [:div.container
       (all-cards @cards)
       [:div.row.mt-5
        [:div.col-sm-6.offset-sm-3.text-center
         [form]]]])))
