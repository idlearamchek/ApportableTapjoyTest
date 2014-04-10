//
//  TapjoyWrapper.h
//
//  Created by Ghislain Bernier on 02/04/14.
//  Copyright (c) 2014 All rights reserved.
//

#import <BridgeKit/JavaObject.h>
#import <BridgeKit/AndroidActivity.h>

@interface TapjoyWrapper : JavaObject

- (id)initWithActivity:(AndroidActivity*)activity;

- (void)setupTapjoy;

@end
